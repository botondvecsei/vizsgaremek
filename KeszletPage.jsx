import React, { useEffect, useState } from "react";

const KeszletPage = () => {
  const [termekek, setTermekek] = useState([]);
  const [kivalasztottTermekId, setKivalasztottTermekId] = useState("");
  const [tipus, setTipus] = useState("BE");
  const [mennyiseg, setMennyiseg] = useState("");
  const [megjegyzes, setMegjegyzes] = useState("");

  // TERMÉKEK BETÖLTÉSE BACKENDBŐL
  useEffect(() => {
    fetch("http://localhost:8081/api/termekek")
      .then((res) => res.json())
      .then((data) => setTermekek(data))
      .catch((err) => console.error("Hiba a termékek betöltésekor:", err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const adat = {
      termekId: parseInt(kivalasztottTermekId),
      tipus,
      mennyiseg: parseInt(mennyiseg),
      megjegyzes,
    };

    try {
      const response = await fetch(
        "http://localhost:8081/api/keszlet/mozgas",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(adat),
        }
      );

      if (!response.ok) {
        throw new Error("Hiba a mentés során");
      }

      alert("Mozgás sikeresen rögzítve!");

      // mezők ürítése
      setKivalasztottTermekId("");
      setMennyiseg("");
      setMegjegyzes("");
      setTipus("BE");
    } catch (error) {
      console.error(error);
      alert("Hiba történt a mentés során!");
    }
  };

  return (
    <div>
      <h2>Készletmozgás rögzítése</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Termék:</label>
          <select
            value={kivalasztottTermekId}
            onChange={(e) => setKivalasztottTermekId(e.target.value)}
            required
          >
            <option value="">-- Válassz terméket --</option>
            {termekek.map((t) => (
              <option key={t.id} value={t.id}>
                {t.nev}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label>Típus:</label>
          <select value={tipus} onChange={(e) => setTipus(e.target.value)}>
            <option value="BE">Bevételezés</option>
            <option value="KI">Kivét</option>
          </select>
        </div>

        <div>
          <label>Mennyiség:</label>
          <input
            type="number"
            value={mennyiseg}
            onChange={(e) => setMennyiseg(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Megjegyzés:</label>
          <input
            type="text"
            value={megjegyzes}
            onChange={(e) => setMegjegyzes(e.target.value)}
          />
        </div>

        <button type="submit">Mozgás rögzítése</button>
      </form>
    </div>
  );
};

export default KeszletPage;
