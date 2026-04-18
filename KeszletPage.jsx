import { useState, useEffect } from 'react';
import { ArrowUpCircle, ArrowDownCircle } from 'lucide-react';
import { toast } from 'sonner';

export default function KeszletPage() {
  const [termekek, setTermekek] = useState([]);
  const [selectedTermek, setSelectedTermek] = useState("");
  const [mennyiseg, setMennyiseg] = useState(0);
  const [tipus, setTipus] = useState("BE"); 
  const [megjegyzes, setMegjegyzes] = useState("");

  
  useEffect(() => {
    setTermekek([
      { id: 1, nev: "Csavar M6x50", jelenlegiSzint: 245 },
      { id: 2, nev: "Anya M8", jelenlegiSzint: 45 },
      { id: 3, nev: "Fúrógép akku", jelenlegiSzint: 12 },
    ]);
  }, []);

  const mozgatas = (e) => {
    e.preventDefault();

    const mozgasAdat = {
      termekId: parseInt(selectedTermek),
      mennyiseg: mennyiseg,
      tipus: tipus,
      megjegyzes: megjegyzes
    };

    fetch("http://localhost:8081/api/keszlet/mozgas", {
      method: "POST", 
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(mozgasAdat) 
    })
    .then(response => {
      if (response.ok) {
        toast.success("Sikeres mentés az adatbázisba!");
      } else {
        toast.error("Hiba történt a mentés során!");
      }
    })
    .catch(error => console.error("Hiba:", error));
  };
  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-6">Készlet Mozgás</h1>

      <div className="grid md:grid-cols-2 gap-6">
        {/* Mozgás rögzítése */}
        <div className="bg-white p-6 rounded-2xl shadow">
          <h2 className="text-xl font-semibold mb-4">Új készletmozgás</h2>
          
          <form onSubmit={mozgatas} className="space-y-5">
            <div>
              <label className="block text-sm font-medium mb-1">Termék</label>
              <select 
                value={selectedTermek} 
                onChange={(e) => setSelectedTermek(e.target.value)}
                className="w-full border rounded-lg px-4 py-3"
                required
              >
                <option value="">Válassz terméket...</option>
                {termekek.map(t => (
                  <option key={t.id} value={t.id}>{t.nev}</option>
                ))}
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Mozgás típusa</label>
              <div className="flex gap-3">
                <button 
                  type="button"
                  onClick={() => setTipus("BE")}
                  className={`flex-1 py-3 rounded-xl flex items-center justify-center gap-2 ${tipus === "BE" ? "bg-green-600 text-white" : "bg-gray-100"}`}
                >
                  <ArrowDownCircle size={20} />
                  Bevétel
                </button>
                <button 
                  type="button"
                  onClick={() => setTipus("KI")}
                  className={`flex-1 py-3 rounded-xl flex items-center justify-center gap-2 ${tipus === "KI" ? "bg-red-600 text-white" : "bg-gray-100"}`}
                >
                  <ArrowUpCircle size={20} />
                  Kivétel
                </button>
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Mennyiség</label>
              <input 
                type="number" 
                min="1"
                value={mennyiseg} 
                onChange={(e) => setMennyiseg(parseInt(e.target.value) || 0)}
                className="w-full border rounded-lg px-4 py-3"
                required 
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Megjegyzés (opcionális)</label>
              <textarea 
                value={megjegyzes} 
                onChange={(e) => setMegjegyzes(e.target.value)}
                className="w-full border rounded-lg px-4 py-3 h-24"
                placeholder="pl. Ügyfél rendelés..."
              />
            </div>

            <button 
              type="submit"
              className="w-full bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-xl font-medium text-lg"
            >
              Mozgás rögzítése
            </button>
          </form>
        </div>

        {/* Aktuális készletek */}
        <div className="bg-white p-6 rounded-2xl shadow">
          <h2 className="text-xl font-semibold mb-4">Aktuális Készletek</h2>
          
          <div className="space-y-3">
            {termekek.map(t => (
              <div key={t.id} className="border rounded-xl p-4 hover:bg-gray-50">
                <div className="flex justify-between items-center">
                  <div>
                    <p className="font-medium">{t.nev}</p>
                  </div>
                  <div className="text-right">
                    <span className={`text-2xl font-bold ${t.jelenlegiSzint <= 50 ? "text-red-600" : "text-green-600"}`}>
                      {t.jelenlegiSzint}
                    </span>
                    <span className="text-sm text-gray-500"> db</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
