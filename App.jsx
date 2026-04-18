import { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Toaster } from "sonner";

import LoginPage from "./pages/LoginPage";
import Index from "./pages/Index";
import KeszletRoute from "./pages/KeszletRoute";

function App() {
  const [user, setUser] = useState(null);

  return (
    <Router>
      <Routes>
        <Route 
          path="/" 
          element={user ? <Index user={user} setUser={setUser} /> : <LoginPage setUser={setUser} />} 
        />
        <Route 
          path="/keszlet" 
          element={user ? <KeszletRoute user={user} setUser={setUser} /> : <LoginPage setUser={setUser} />} 
        />
      </Routes>
      <Toaster position="top-center" richColors />
    </Router>
  );
}

export default App;