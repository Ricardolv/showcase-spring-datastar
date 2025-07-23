/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/jte/**/*.jte",
    "./src/main/resources/static/js/**/*.js"
  ],
  theme: {
    colors: {
      // Cores padrão necessárias
      white: '#ffffff',
      black: '#000000',
      
      // Paleta CEAR: Branco, Preto, Laranjado
      primary: {
        50: '#fff7ed',    // Laranja muito claro
        100: '#ffedd5',   // Laranja claro
        200: '#fed7aa',   // Laranja suave
        300: '#fdba74',   // Laranja médio claro
        400: '#fb923c',   // Laranja médio
        500: '#f97316',   // Laranja principal CEAR
        600: '#ea580c',   // Laranja escuro
        700: '#c2410c',   // Laranja muito escuro
        800: '#9a3412',   // Laranja profundo
        900: '#7c2d12',   // Laranja máximo
        950: '#431407'    // Laranja quase preto
      },
      
      secondary: {
        50: '#f9fafb',    // Branco suave
        100: '#f3f4f6',   // Cinza muito claro
        200: '#e5e7eb',   // Cinza claro
        300: '#d1d5db',   // Cinza médio claro
        400: '#9ca3af',   // Cinza médio
        500: '#6b7280',   // Cinza
        600: '#4b5563',   // Cinza escuro
        700: '#374151',   // Cinza muito escuro
        800: '#1f2937',   // Preto suave
        900: '#111827',   // Preto
        950: '#030712'    // Preto profundo
      }
    },
    extend: {
      
      fontFamily: {
        sans: ['Inter', 'system-ui', '-apple-system', 'sans-serif'],
        mono: ['JetBrains Mono', 'Monaco', 'Consolas', 'monospace']
      },
      
      boxShadow: {
        'orange': '0 1px 3px 0 rgba(249, 115, 22, 0.1), 0 1px 2px 0 rgba(249, 115, 22, 0.06)',
        'orange-md': '0 4px 6px -1px rgba(249, 115, 22, 0.1), 0 2px 4px -1px rgba(249, 115, 22, 0.06)',
        'orange-lg': '0 10px 15px -3px rgba(249, 115, 22, 0.1), 0 4px 6px -2px rgba(249, 115, 22, 0.05)'
      },
      
      animation: {
        'bounce-in': 'bounceIn 0.6s ease-out',
        'slide-up': 'slideUp 0.4s ease-out',
        'fade-in': 'fadeIn 0.3s ease-out'
      }
    }
  },
  
  plugins: [
    function({ addUtilities }) {
      const newUtilities = {
        '.bg-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)'
        },
        '.text-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)',
          '-webkit-background-clip': 'text',
          '-webkit-text-fill-color': 'transparent'
        }
      }
      addUtilities(newUtilities)
    }
  ]
} 