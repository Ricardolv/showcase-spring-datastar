/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/jte/**/*.jte",
    "./src/main/resources/static/js/**/*.js"
  ],
  theme: {
    extend: {
      colors: {
        // Paleta CEAR: Laranja como primary
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
        
        // Mapeamento das cores CEAR para orange (padrão Tailwind)
        orange: {
          50: '#fff7ed',    
          100: '#ffedd5',   
          200: '#fed7aa',   
          300: '#fdba74',   
          400: '#fb923c',   
          500: '#f97316',   // Cor principal CEAR
          600: '#ea580c',   
          700: '#c2410c',   
          800: '#9a3412',   
          900: '#7c2d12',   
          950: '#431407'    
        }
      },
      
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
      },
      
      keyframes: {
        bounceIn: {
          '0%': { opacity: '0', transform: 'scale(0.3)' },
          '50%': { opacity: '1', transform: 'scale(1.05)' },
          '70%': { transform: 'scale(0.9)' },
          '100%': { opacity: '1', transform: 'scale(1)' }
        },
        slideUp: {
          '0%': { opacity: '0', transform: 'translateY(30px)' },
          '100%': { opacity: '1', transform: 'translateY(0)' }
        },
        fadeIn: {
          '0%': { opacity: '0' },
          '100%': { opacity: '1' }
        }
      }
    }
  },
  
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
    function({ addUtilities }) {
      const newUtilities = {
        '.bg-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)'
        },
        '.text-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)',
          '-webkit-background-clip': 'text',
          '-webkit-text-fill-color': 'transparent',
          'background-clip': 'text'
        }
      }
      addUtilities(newUtilities)
    }
  ]
} 