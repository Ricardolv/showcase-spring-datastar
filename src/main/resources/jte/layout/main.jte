@import gg.jte.Content

@param String title = "CEAR Showcase"
@param Content content

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - CEAR Showcase</title>
    
    <!-- Fonte Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Tailwind CSS -->
    <link href="/css/styles.css" rel="stylesheet">
    
    <!-- Datastar CDN -->
    <script type="module" defer src="https://cdn.jsdelivr.net/npm/@sudodevnull/datastar@latest"></script>
</head>
<body class="bg-gray-50 font-sans">
    <header class="bg-white shadow-sm border-b border-gray-200 sticky top-0 z-40">
        @template.components.navbar()
    </header>
    
    <main class="min-h-screen">
        ${content}
    </main>
    
    <footer class="bg-gray-900 text-gray-300 py-12 mt-16">
        <div class="container-showcase">
            <div class="text-center">
                <p>&copy; 2024 CEAR - UFPB. Projeto de demonstração.</p>
            </div>
        </div>
    </footer>
</body>
</html> 