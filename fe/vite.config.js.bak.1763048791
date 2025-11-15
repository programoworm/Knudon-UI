import { defineConfig } from 'vite';
import React from "react";

export default defineConfig({
  plugins: [React()],
  server: {
    host: "0.0.0.0", // Allow external access
    port: 4173, // Match the frontend port
    strictPort: true,
  },
  preview: {
    allowedHosts: ["rajarshibhattacharya.in"], // 👈 Fix for blocked host issue
  }
});
