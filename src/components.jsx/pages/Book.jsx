
// 9. PAGE - src/pages/Book.jsx
import { useParams } from "react-router-dom";

export default function Book() {
  const { trainId } = useParams();
  return (
    <div className="max-w-xl mx-auto py-10">
      <h2 className="text-2xl font-bold text-[#169258] mb-4">Book Train: {trainId}</h2>
      <form className="space-y-4">
        <input className="w-full p-2 border rounded" placeholder="Passenger Name" />
        <input className="w-full p-2 border rounded" placeholder="Date of Travel" type="date" />
        <button className="w-full bg-[#169258] text-white py-2 rounded">Confirm Booking</button>
      </form>
    </div>
  );
}
