import { LogOut } from 'lucide-react';

export default function AppLayout({ user, setUser, children }) {
  const logout = () => {
    if (window.confirm("Biztosan ki akarsz lépni?")) {
      setUser(null);
    }
  };

  return (
    <div className="min-h-screen bg-slate-50">
      {/* Fejléc */}
      <header className="bg-white border-b shadow-sm">
        <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
          <div className="flex items-center gap-3">
            <div className="w-9 h-9 bg-blue-600 rounded-xl flex items-center justify-center">
              <span className="text-white font-bold text-xl">R</span>
            </div>
            <div>
              <h1 className="font-bold text-xl">Raktár Kezelő</h1>
              <p className="text-xs text-gray-500 -mt-1">Diák projekt</p>
            </div>
          </div>

          <div className="flex items-center gap-4">
            <div className="text-right">
              <p className="font-medium text-sm">{user?.nev}</p>
              <p className="text-xs text-gray-500">{user?.jogkor}</p>
            </div>
            
            <button 
              onClick={logout}
              className="flex items-center gap-2 bg-red-50 hover:bg-red-100 text-red-600 px-4 py-2 rounded-xl text-sm font-medium"
            >
              <LogOut size={18} />
              Kilépés
            </button>
          </div>
        </div>
      </header>

      {/* Tartalom */}
      <main className="max-w-7xl mx-auto">
        {children}
      </main>
    </div>
  );
}