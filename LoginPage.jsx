import { useState } from 'react';
import { Package, User } from 'lucide-react';

export default function LoginPage({ setUser }) {
  const [email, setEmail] = useState("");
  const [jelszo, setJelszo] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    setTimeout(() => {
      setUser({
        id: 1,
        nev: "Teszt Elek",
        email: email || "teszt@raktr.hu",
        jogkor: "RAKTAROS"
      });
      setLoading(false);
    }, 700);
  };

  const demoLogin = () => {
    console.log("Demo belépés aktiválva!");
    setUser({
      id: 999,
      nev: "Demo",
      email: "demo@raktr.hu",
      jogkor: "RAKTAROS"   
    });
  };

  return (
    <div className="min-h-screen bg-slate-100 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl shadow-xl w-full max-w-md p-8">
        <div className="flex flex-col items-center mb-8">
          <div className="w-20 h-20 bg-blue-600 rounded-2xl flex items-center justify-center mb-4">
            <Package className="text-white" size={40} />
          </div>
          <h1 className="text-3xl font-bold text-gray-800">Raktár Kezelő</h1>
          
        </div>

        <form onSubmit={handleLogin} className="space-y-5">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:border-blue-500"
              placeholder="pelda@email.com"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Jelszó</label>
            <input
              type="password"
              value={jelszo}
              onChange={(e) => setJelszo(e.target.value)}
              className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:border-blue-500"
              placeholder="••••••••"
              required
            />
          </div>

          {error && <p className="text-red-500 text-center">{error}</p>}

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-3.5 rounded-xl transition"
          >
            {loading ? "Bejelentkezés folyamatban..." : "Bejelentkezés"}
          </button>
        </form>

        <div className="my-6 text-center text-gray-400 text-sm">vagy</div>

        <button
          onClick={demoLogin}
          className="w-full border-2 border-dashed border-gray-300 hover:border-gray-400 text-gray-700 font-medium py-3.5 rounded-xl flex items-center justify-center gap-2 transition"
        >
          <User size={20} />
          Demo belépés (teszteléshez)
        </button>

        <p className="text-center text-xs text-gray-400 mt-8">
        
        </p>
      </div>
    </div>
  );
}