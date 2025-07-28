import React, { useEffect, useState } from "react";

function AddPlayerForm({ onPlayerAdded }) {
  const [form, setForm] = useState({ name: "", team: "", pos: "", nation: "" });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setError("");
    fetch("http://localhost:8080/api/v1/player", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to add player");
        return res.json();
      })
      .then((newPlayer) => {
        onPlayerAdded(newPlayer);
        setForm({ name: "", team: "", pos: "", nation: "" });
      })
      .catch((err) => setError(err.message));
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
      <input name="name" value={form.name} onChange={handleChange} placeholder="Name" required />
      <input name="team" value={form.team} onChange={handleChange} placeholder="Team" required />
      <input name="pos" value={form.pos} onChange={handleChange} placeholder="Position" required />
      <input name="nation" value={form.nation} onChange={handleChange} placeholder="Nation" required />
      <button type="submit">Add Player</button>
      {error && <span style={{ color: "red", marginLeft: 10 }}>{error}</span>}
    </form>
  );
}

function App() {
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/player")
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch players");
        return res.json();
      })
      .then((data) => {
        setPlayers(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  const handleDelete = (name) => {
    fetch(`http://localhost:8080/api/v1/player/${encodeURIComponent(name)}`, { method: "DELETE" })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to delete player");
        setPlayers(players.filter((p) => p.name !== name));
      })
      .catch((err) => setError(err.message));
  };

  const handlePlayerAdded = (player) => {
    setPlayers([...players, player]);
  };

  return (
    <div style={{ maxWidth: 600, margin: "40px auto", fontFamily: "Arial, sans-serif" }}>
      <h1>Player Directory</h1>
      <AddPlayerForm onPlayerAdded={handlePlayerAdded} />
      {loading ? (
        <p>Loading players...</p>
      ) : error ? (
        <p style={{ color: "red" }}>{error}</p>
      ) : (
        <ul style={{ listStyle: "none", padding: 0 }}>
          {players.map((player) => (
            <li key={player.name} style={{ marginBottom: 10, borderBottom: "1px solid #eee", paddingBottom: 6 }}>
              <strong>{player.name}</strong> ({player.team}) - {player.pos} [{player.nation}]
              <button
                onClick={() => handleDelete(player.name)}
                style={{ marginLeft: 10, color: "white", background: "#d9534f", border: "none", borderRadius: 3, padding: "2px 8px", cursor: "pointer" }}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
