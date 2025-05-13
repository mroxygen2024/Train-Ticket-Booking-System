// 8. PAGE - src/pages/Register.jsx
export default function Register() {
  return (
    <div className="max-w-md mx-auto py-10">
      <h2 className="text-2xl font-bold text-[#169258] mb-4">Register</h2>
      <form className="space-y-4">
        <input className="w-full p-2 border rounded" placeholder="Full Name" type="text" />
        <input className="w-full p-2 border rounded" placeholder="Email" type="email" />
        <input className="w-full p-2 border rounded" placeholder="Password" type="password" />
        <button className="w-full bg-[#169258] text-white py-2 rounded">Create Account</button>
      </form>
    </div>
  );
}