import AppLayout from "../components/AppLayout";
import KeszletPage from "./KeszletPage";

export default function KeszletRoute({ user, setUser }) {
  return (
    <AppLayout user={user} setUser={setUser}>
      <KeszletPage />
    </AppLayout>
  );
}