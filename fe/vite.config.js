import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: "0.0.0.0", // Allow external access
    port: 4173,
    strictPort: true,
  },
  preview: {
    // keep your allowed hosts (adjust if needed)
    allowedHosts: ["rajarshibhattacharya.in"],
  }
})
