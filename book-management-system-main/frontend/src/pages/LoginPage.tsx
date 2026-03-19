import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { LoginDto } from '@/types/LoginType';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { Label } from '@/components/ui/label';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faEnvelope,
  faLock,
  faEye,
  faEyeSlash,
  faSpinner,
  faArrowRightToBracket,
  faCircleExclamation,
} from '@fortawesome/free-solid-svg-icons';
import api from '@/lib/axiosInstance';
import axios from 'axios';
import type { ErrorDto } from '@/types/Error';

const LoginPage = () => {
  const [formData, setFormData] = useState<LoginDto>({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
    if (error) setError('');
  };

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const { data } = await api.post('/auth/login', formData);
      localStorage.setItem('accessToken', data.accessToken);
      localStorage.setItem('refreshToken', data.refreshToken);
      navigate('/dashboard');
    } catch (err) {
      if (axios.isAxiosError<ErrorDto>(err) && err.response?.data?.message) {
        setError(err.response.data.message);
      } else {
        setError('Something went wrong. Please try again.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Left panel — cover image */}
      <div className="hidden md:block w-1/2 h-full">
        <img src="/cover.jpg" alt="Library" className="h-full w-full object-cover" />
      </div>

      {/* Right panel — form fills the panel directly, no inner card */}
      <div className="w-full md:w-1/2 h-full flex items-center justify-center bg-[#F8F2E8] px-12">
        <div className="w-full max-w-md">
          {/* Logo */}
          <div className="flex justify-center mb-8">
            <div
              className="w-16 h-16 rounded-full flex items-center justify-center text-[#F0DDB8] font-bold text-sm"
              style={{ background: '#2E1505' }}
            >
              LOGO
            </div>
          </div>

          {/* Heading */}
          <h1 className="font-serif text-4xl font-bold text-[#1A0F06] text-center mb-2 tracking-tight">
            Sign In
          </h1>
          <p className="text-center text-[#7A5C38] text-sm mb-10 leading-relaxed">
            Streamlining your workflow, one volume at a time.
          </p>

          {/* Form */}
          <form onSubmit={handleLogin} noValidate className="space-y-5">
            {/* Email */}
            <div>
              <Label
                htmlFor="email"
                className="text-[10px] font-medium tracking-[0.18em] uppercase text-[#5C3D1C]"
              >
                Email Address
              </Label>
              <div className="relative mt-2">
                <span className="absolute left-3.5 top-1/2 -translate-y-1/2 text-[#9A7040] text-sm pointer-events-none">
                  <FontAwesomeIcon icon={faEnvelope} />
                </span>
                <Input
                  id="email"
                  name="email"
                  type="email"
                  placeholder="you@example.com"
                  value={formData.email}
                  onChange={handleChange}
                  autoComplete="email"
                  required
                  className="pl-10 h-12 bg-[#FDF6EA] border-[#D9C4A0] rounded-lg text-[#1A0F06] placeholder:text-[#C5A87A] focus-visible:ring-[#8B5A1F]/30 focus-visible:border-[#8B5A1F]"
                />
              </div>
            </div>

            {/* Password */}
            <div>
              <Label
                htmlFor="password"
                className="text-[10px] font-medium tracking-[0.18em] uppercase text-[#5C3D1C]"
              >
                Password
              </Label>
              <div className="relative mt-2">
                <span className="absolute left-3.5 top-1/2 -translate-y-1/2 text-[#9A7040] text-sm pointer-events-none">
                  <FontAwesomeIcon icon={faLock} />
                </span>
                <Input
                  id="password"
                  name="password"
                  type={showPassword ? 'text' : 'password'}
                  placeholder="••••••••"
                  value={formData.password}
                  onChange={handleChange}
                  autoComplete="current-password"
                  required
                  className="pl-10 pr-10 h-12 bg-[#FDF6EA] border-[#D9C4A0] rounded-lg text-[#1A0F06] placeholder:text-[#C5A87A] focus-visible:ring-[#8B5A1F]/30 focus-visible:border-[#8B5A1F]"
                />
                <button
                  type="button"
                  tabIndex={-1}
                  onClick={() => setShowPassword((v) => !v)}
                  className="absolute right-3.5 top-1/2 -translate-y-1/2 text-[#9A7040] hover:text-[#4A2A0A] transition-colors"
                >
                  <FontAwesomeIcon icon={showPassword ? faEyeSlash : faEye} className="text-sm" />
                </button>
              </div>
            </div>

            {/* Error */}
            {error && (
              <div
                role="alert"
                className="flex items-start gap-2.5 rounded-lg px-3.5 py-3 text-sm text-[#7A2E10] bg-[#FDF1EC] border border-[#E8A88A] border-l-[3px] border-l-[#C0521F]"
              >
                <FontAwesomeIcon
                  icon={faCircleExclamation}
                  className="text-[#C0521F] mt-0.5 shrink-0"
                />
                <span>{error}</span>
              </div>
            )}

            {/* Submit */}
            <Button
              type="submit"
              disabled={loading}
              className="w-full h-12 text-[#F0DDB8] font-medium tracking-widest uppercase text-xs flex items-center justify-center gap-2.5 rounded-lg transition-all"
              style={{ background: '#2E1505', boxShadow: '0 6px 24px rgba(46,21,5,0.32)' }}
            >
              <FontAwesomeIcon
                icon={loading ? faSpinner : faArrowRightToBracket}
                className={loading ? 'animate-spin' : ''}
              />
              {loading ? 'Signing in…' : 'Sign In'}
            </Button>
          </form>

          {/* Footer */}
          <div className="flex items-center gap-3 mt-8">
            <div className="flex-1 h-px bg-[#D9C4A0]" />
            <span className="text-[9.5px] tracking-[0.18em] uppercase text-[#A88A60]">
              Secure Access
            </span>
            <div className="flex-1 h-px bg-[#D9C4A0]" />
          </div>
          <p className="mt-5 text-center text-xs text-[#A88A60]">
            Having trouble? Contact your{' '}
            <strong className="font-medium text-[#6B4520]">system administrator</strong>.
          </p>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
