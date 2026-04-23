import AppLayout from "../components/AppLayout";
import TermekekPage from "./TermekekPage";

// Fő (dashboard) oldal komponens
// Ez tölti be az alap layoutot és a termékek oldalt
export default function Index({ user, setUser }) {
  return (
    <AppLayout user={user} setUser={setUser}>
      <TermekekPage />
    </AppLayout>
  );
}
