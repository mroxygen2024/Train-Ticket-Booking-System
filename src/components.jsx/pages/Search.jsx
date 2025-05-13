// 6. PAGE - src/pages/Search.jsx
export default function Search() {
  return (
    <div className="max-w-2xl mx-auto py-10">
      <h2 className="text-2xl font-bold text-[#169258] mb-4">Search Trains</h2>
      <input
        type="text"
        placeholder="Enter destination or train name"
        className="w-full p-2 border rounded focus:outline-[#169258]"
      />
    </div>
  );
}