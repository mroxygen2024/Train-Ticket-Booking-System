// 7. PAGE - src/pages/Login.jsx
export default function Login() {
  return (
    <div className="max-w-md mx-auto py-10">
      <h2 className="text-2xl font-bold text-[#169258] mb-4">Login</h2>
      <form className="space-y-4">
        <input className="w-full p-2 border rounded" placeholder="Email" type="email" />
        <input className="w-full p-2 border rounded" placeholder="Password" type="password" />
        <button className="w-full bg-[#169258] text-white py-2 rounded">Login</button>
      </form>
    </div>
  );
}