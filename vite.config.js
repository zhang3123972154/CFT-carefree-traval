import { defineConfig } from "vite";
import uni from "@dcloudio/vite-plugin-uni";
import { fileURLToPath, URL } from "node:url";

// import commonjs from '@rollup/plugin-commonjs'

/**
 * @type {import('vite').UserConfig}
 */

export default defineConfig({
  build: {
    sourcemap: false, // App，小程序端源码调试，需要在 vite.config.js 中主动开启 sourcemap
    rollupOptions: {
    }
  },
  define: {
      __VUE_I18N_LEGACY_API__: false,
      __VUE_I18N_FULL_INSTALL__: false,
      __INTLIFY_PROD_DEVTOOLS__: false,
  },
  plugins: [
	// commonjs(),
	  uni()
  ],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./pages", import.meta.url)),
    }
  }
});
