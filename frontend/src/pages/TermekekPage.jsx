import { useState, useEffect } from 'react';
import { Plus, Trash2, Edit2, AlertTriangle, X, Save, ShoppingCart, Truck, PackageCheck } from 'lucide-react';
import { toast } from 'sonner';

export default function TermekekPage() {
  const [termekek, setTermekek] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);

  // Rendelési státuszok termékenként: { [id]: 'LEADVA' | 'UTON' }
  const [rendelesStatusz, setRendelesStatusz] = useState({});

  const initialFormState = { nev: '', jelenlegiSzint: 0, minSzint: 0, ajanlottSzint: 0 };
  const [formData, setFormData] = useState(initialFormState);

  // ADATOK LEKÉRÉSE (GET)
  const loadData = async () => {
    try {
      const res = await fetch("http://localhost:8081/api/termek");
      const data = await res.json();
      setTermekek(data);
    } catch (err) {
      toast.error("Hiba a termékek betöltésekor!");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { loadData(); }, []);

  // MENTÉS ÉS SZERKESZTÉS (POST / PUT)
  const handleSubmit = async (e) => {
    e.preventDefault();
    const isEditing = editingId !== null;
    const url = isEditing 
      ? `http://localhost:8081/api/termek/${editingId}` 
      : "http://localhost:8081/api/termek";
    
    try {
      const res = await fetch(url, {
        method: isEditing ? 'PUT' : 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      });

      if (res.ok) {
        toast.success(isEditing ? "Termék frissítve!" : "Új termék rögzítve!");
        resetForm();
        loadData();
      }
    } catch (err) {
      toast.error("Hiba történt a mentés során!");
    }
  };

  // TÖRLÉS (DELETE)
  const handleDelete = async (id) => {
    if (!confirm("Biztosan törli a terméket?")) return;
    try {
      await fetch(`http://localhost:8081/api/termek/${id}`, { method: 'DELETE' });
      toast.success("Termék eltávolítva!");
      loadData();
    } catch (err) {
      toast.error("Hiba a törlés során!");
    }
  };

  const startEdit = (termek) => {
    setEditingId(termek.id);
    setFormData({
      nev: termek.nev,
      jelenlegiSzint: termek.jelenlegiSzint,
      minSzint: termek.minSzint,
      ajanlottSzint: termek.ajanlottSzint
    });
    setShowForm(true);
  };

  const resetForm = () => {
    setFormData(initialFormState);
    setEditingId(null);
    setShowForm(false);
  };

  // BERENDELÉS folyamat: LEADVA -> UTON -> MEGÉRKEZETT (alert + készlet+x)
  const handleBerendel = (termek) => {
    const aktualis = rendelesStatusz[termek.id];

    // Mennyi kell az ajánlott szintig (legalább 1)
    const mennyiseg = Math.max(1, (termek.ajanlottSzint || 0) - (termek.jelenlegiSzint || 0));

    if (!aktualis) {
      // 1. lépés: rendelés leadva
      setRendelesStatusz(prev => ({ ...prev, [termek.id]: 'LEADVA' }));
      toast.success(`Rendelés leadva: ${termek.nev} (${mennyiseg} db)`);

      // 2. lépés automatikusan: termék úton
      setTimeout(() => {
        setRendelesStatusz(prev => ({ ...prev, [termek.id]: 'UTON' }));
        toast.info(`${termek.nev} úton van...`);
      }, 2000);
      return;
    }

    if (aktualis === 'LEADVA') {
      setRendelesStatusz(prev => ({ ...prev, [termek.id]: 'UTON' }));
      toast.info(`${termek.nev} úton van...`);
      return;
    }

    if (aktualis === 'UTON') {
      // 3. lépés: megérkezett -> alert + készlet növelés + DB mentés
      megerkezett(termek, mennyiseg);
    }
  };

  const megerkezett = async (termek, mennyiseg) => {
    const ujSzint = (termek.jelenlegiSzint || 0) + mennyiseg;

    try {
      const res = await fetch(`http://localhost:8081/api/termek/${termek.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nev: termek.nev,
          jelenlegiSzint: ujSzint,
          minSzint: termek.minSzint,
          ajanlottSzint: termek.ajanlottSzint
        })
      });

      if (res.ok) {
        alert(`A termékek megjöttek! ${termek.nev}: +${mennyiseg} db hozzáadva (új szint: ${ujSzint}).`);
        setRendelesStatusz(prev => {
          const copy = { ...prev };
          delete copy[termek.id];
          return copy;
        });
        loadData();
      } else {
        toast.error("Hiba a készlet frissítésekor!");
      }
    } catch (err) {
      toast.error("Hiba történt a berendelés véglegesítésekor!");
    }
  };

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-2xl font-bold text-gray-800">Termékek Kezelése</h1>
        <button 
          onClick={() => { if(showForm) resetForm(); else setShowForm(true); }}
          className="bg-blue-600 text-white px-4 py-2 rounded-lg flex items-center gap-2 hover:bg-blue-700 transition"
        >
          {showForm ? <X size={20}/> : <Plus size={20}/>}
          {showForm ? "Mégsem" : "Új termék"}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-xl shadow-sm border mb-8 grid grid-cols-1 md:grid-cols-4 gap-4">
          <div className="md:col-span-2">
            <label className="text-sm font-medium text-gray-600">Termék neve</label>
            <input 
              type="text" required
              className="w-full border rounded-md p-2 mt-1"
              value={formData.nev}
              onChange={e => setFormData({...formData, nev: e.target.value})}
            />
          </div>
          <div>
            <label className="text-sm font-medium text-gray-600">Jelenlegi szint</label>
            <input 
              type="number" required
              className="w-full border rounded-md p-2 mt-1"
              value={formData.jelenlegiSzint || 0}
                onChange={e => {
                  const val = e.target.value;
                  setFormData({
                    ...formData, 
                    jelenlegiSzint: val === "" ? 0 : parseInt(val)
                  });
                }}
            />
          </div>
          <div>
            <label className="text-sm font-medium text-gray-600">Minimum szint</label>
            <input 
              type="number" required
              className="w-full border rounded-md p-2 mt-1"
              value={formData.minSzint}
              onChange={e => setFormData({...formData, minSzint: parseInt(e.target.value)})}
            />
          </div>
          <div>
            <label className="text-sm font-medium text-gray-600">Ajánlott szint</label>
            <input 
              type="number" required
              className="w-full border rounded-md p-2 mt-1"
              value={formData.ajanlottSzint}
              onChange={e => setFormData({...formData, ajanlottSzint: parseInt(e.target.value)})}
            />
          </div>
          <div className="md:col-span-4 flex justify-end">
            <button type="submit" className="bg-green-600 text-white px-6 py-2 rounded-lg flex items-center gap-2 hover:bg-green-700">
              <Save size={18}/> {editingId ? "Módosítás mentése" : "Termék hozzáadása"}
            </button>
          </div>
        </form>
      )}

      <div className="bg-white rounded-xl shadow-sm border overflow-hidden">
        <table className="w-full text-left">
          <thead className="bg-gray-50 border-b">
            <tr>
              <th className="p-4 text-sm font-semibold text-gray-600">ID</th>
              <th className="p-4 text-sm font-semibold text-gray-600">Termék neve</th>
              <th className="p-4 text-sm font-semibold text-gray-600 text-center">Jelenlegi</th>
              <th className="p-4 text-sm font-semibold text-gray-600 text-center">Státusz</th>
              <th className="p-4 text-sm font-semibold text-gray-600 text-right">Műveletek</th>
            </tr>
          </thead>
          <tbody>
            {termekek.map(t => {
              const rStat = rendelesStatusz[t.id];
              return (
                <tr key={t.id} className="border-b hover:bg-gray-50 transition">
                  <td className="p-4 text-gray-400">#{t.id}</td>
                  <td className="p-4 font-medium text-gray-800">{t.nev}</td>
                  <td className="p-4 text-center font-bold">{t.jelenlegiSzint}</td>
                  <td className="p-4 text-center">
                    <div className="flex flex-col items-center gap-2">
                      {t.jelenlegiSzint <= t.minSzint ? (
                        <span className="bg-red-50 text-red-600 px-2 py-1 rounded text-xs font-bold flex items-center justify-center gap-1">
                          <AlertTriangle size={12}/> Alacsony
                        </span>
                      ) : (
                        <span className="bg-green-50 text-green-600 px-2 py-1 rounded text-xs font-bold">OK</span>
                      )}

                      {/* Rendelési státusz a státusz alatt */}
                      {rStat === 'LEADVA' && (
                        <span className="bg-yellow-50 text-yellow-700 px-2 py-1 rounded text-xs font-bold flex items-center gap-1">
                          <ShoppingCart size={12}/> Rendelés leadva
                        </span>
                      )}
                      {rStat === 'UTON' && (
                        <span className="bg-blue-50 text-blue-700 px-2 py-1 rounded text-xs font-bold flex items-center gap-1">
                          <Truck size={12}/> Termék úton
                        </span>
                      )}
                    </div>
                  </td>
                  <td className="p-4 text-right">
                    <div className="flex justify-end gap-2">
                      <button
                        onClick={() => handleBerendel(t)}
                        className="p-2 text-emerald-700 bg-emerald-50 hover:bg-emerald-100 rounded-md flex items-center gap-1 text-xs font-semibold"
                        title="Berendelés"
                      >
                        {rStat === 'UTON' ? <PackageCheck size={16}/> : <ShoppingCart size={16}/>}
                        {rStat === 'UTON' ? 'Megérkezett' : rStat === 'LEADVA' ? 'Úton →' : 'Berendelés'}
                      </button>
                      <button onClick={() => startEdit(t)} className="p-2 text-blue-600 hover:bg-blue-50 rounded-md">
                        <Edit2 size={18}/>
                      </button>
                      <button onClick={() => handleDelete(t.id)} className="p-2 text-red-600 hover:bg-red-50 rounded-md">
                        <Trash2 size={18}/>
                      </button>
                    </div>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}
