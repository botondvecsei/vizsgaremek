import { useState, useEffect } from 'react';
import { Plus, Trash2, AlertTriangle } from 'lucide-react';
import { toast } from 'sonner';

export default function TermekekPage() {
  const [termekek, setTermekek] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  
  const [ujTermek, setUjTermek] = useState({
    nev: "",
    jelenlegiSzint: 0,
    minSzint: 0,
    ajanlottSzint: 0
  });

  
 useEffect(() => {
    fetch("http://localhost:8081/api/termekek") // Hívjuk a te Java végpontodat
      .then(response => response.json())        // A választ JSON-ná alakítjuk
      .then(data => {
        setTermekek(data);                      // Eltesszük az adatokat a React-be
        setLoading(false);
      })
      .catch(error => {
        console.error("Hiba történt a lekéréskor:", error);
        setLoading(false);
      });
  }, []);

  const hozzaadTermek = (e) => {
    e.preventDefault();
    if (!ujTermek.nev) {
      toast.error("Adj meg egy nevet!");
      return;
    }
    
    const newItem = {
      id: Date.now(),
      ...ujTermek
    };
    
    setTermekek([...termekek, newItem]);
    setUjTermek({ nev: "", jelenlegiSzint: 0, minSzint: 0, ajanlottSzint: 0 });
    setShowForm(false);
    toast.success("Termék sikeresen hozzáadva!");
  };

  const torolTermek = (id) => {
    if (window.confirm("Biztosan törlöd ezt a terméket?")) {
      setTermekek(termekek.filter(t => t.id !== id));
      toast.success("Termék törölve");
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Termékek Kezelése</h1>
        <button 
          onClick={() => setShowForm(!showForm)}
          className="bg-blue-600 text-white px-5 py-2.5 rounded-xl flex items-center gap-2 hover:bg-blue-700"
        >
          <Plus size={20} />
          Új termék
        </button>
      </div>

      {showForm && (
        <div className="bg-white p-6 rounded-2xl shadow mb-6">
          <h2 className="text-xl font-semibold mb-4">Új termék hozzáadása</h2>
          <form onSubmit={hozzaadTermek} className="grid grid-cols-1 md:grid-cols-4 gap-4">
            <div className="md:col-span-4">
              <label className="block text-sm mb-1">Termék neve</label>
              <input
                type="text"
                value={ujTermek.nev}
                onChange={(e) => setUjTermek({...ujTermek, nev: e.target.value})}
                className="w-full border rounded-lg px-4 py-3"
                placeholder="pl. Csavar M6"
                required
              />
            </div>
            
            <div>
              <label className="block text-sm mb-1">Jelenlegi készlet</label>
              <input type="number" value={ujTermek.jelenlegiSzint} onChange={(e) => setUjTermek({...ujTermek, jelenlegiSzint: parseInt(e.target.value)})} className="w-full border rounded-lg px-4 py-3" />
            </div>
            <div>
              <label className="block text-sm mb-1">Minimum szint</label>
              <input type="number" value={ujTermek.minSzint} onChange={(e) => setUjTermek({...ujTermek, minSzint: parseInt(e.target.value)})} className="w-full border rounded-lg px-4 py-3" />
            </div>
            <div>
              <label className="block text-sm mb-1">Ajánlott szint</label>
              <input type="number" value={ujTermek.ajanlottSzint} onChange={(e) => setUjTermek({...ujTermek, ajanlottSzint: parseInt(e.target.value)})} className="w-full border rounded-lg px-4 py-3" />
            </div>

            <div className="md:col-span-4 flex gap-3">
              <button type="submit" className="flex-1 bg-green-600 text-white py-3 rounded-xl">Hozzáadás</button>
              <button type="button" onClick={() => setShowForm(false)} className="flex-1 bg-gray-300 py-3 rounded-xl">Mégse</button>
            </div>
          </form>
        </div>
      )}

      <div className="bg-white rounded-2xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left p-4">ID</th>
              <th className="text-left p-4">Termék neve</th>
              <th className="text-center p-4">Jelenlegi</th>
              <th className="text-center p-4">Minimum</th>
              <th className="text-center p-4">Ajánlott</th>
              <th className="text-center p-4">Státusz</th>
              <th className="w-20"></th>
            </tr>
          </thead>
          <tbody>
            {termekek.map(t => (
              <tr key={t.id} className="border-t hover:bg-gray-50">
                <td className="p-4 font-mono text-gray-500">{t.id}</td>
                <td className="p-4 font-medium">{t.nev}</td>
                <td className="p-4 text-center font-semibold">{t.jelenlegiSzint}</td>
                <td className="p-4 text-center">{t.minSzint}</td>
                <td className="p-4 text-center">{t.ajanlottSzint}</td>
                <td className="p-4 text-center">
                  {t.jelenlegiSzint <= t.minSzint ? (
                    <span className="inline-flex items-center gap-1 text-red-600">
                      <AlertTriangle size={16} /> Alacsony
                    </span>
                  ) : (
                    <span className="text-green-600">OK</span>
                  )}
                </td>
                <td className="p-4 text-center">
                  <button onClick={() => torolTermek(t.id)} className="text-red-500 hover:text-red-700">
                    <Trash2 size={20} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}