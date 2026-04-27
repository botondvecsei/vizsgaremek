import AppLayout from "../components/AppLayout";
import TermekekPage from "./TermekekPage";

export default function Index({ user, setUser }) {
  return (
    <AppLayout user={user} setUser={setUser}>
      <TermekekPage />
    </AppLayout>
  );
}