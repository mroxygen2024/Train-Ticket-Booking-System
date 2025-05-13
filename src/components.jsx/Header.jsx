// 4. COMPONENT - src/components/Header.jsx
import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <header className="bg-black text-white p-4 shadow-md sticky top-0 z-50">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <h1 className="text-2xl font-bold text-[#169258]">🚄 TrainBook</h1>
        <nav className="space-x-4">
          <Link to="/" className="hover:text-[#169258]">Home</Link>
          <Link to="/search" className="hover:text-[#169258]">Search</Link>
          <Link to="/login" className="hover:text-[#169258]">Login</Link>
          <Link to="/register" className="hover:text-[#169258]">Register</Link>
          <Link to="/admin" className="hover:text-[#169258]">Admin</Link>
        </nav>
      </div>
    </header>
  );
}
