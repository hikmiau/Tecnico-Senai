package lojacarrosweb;
public class HtmlUtilCarros {

    // Método que cria o início do HTML.
    public static String inicioHtml(String titulo) {

        // Variável usada para montar o HTML.
        String html = "";

        // Define o tipo do documento.
        html += "<!DOCTYPE html>";

        // Abre a tag HTML com idioma português do Brasil.
        html += "<html lang='pt-br'>";

        // Abre o cabeçalho da página.
        html += "<head>";

        // Define a codificação dos caracteres.
        html += "<meta charset='UTF-8'>";

        // Define o título da aba do navegador.
        html += "<title>" + titulo + "</title>";

        // Início do CSS.
        html += "<style>";

        html += "body {";
        html += "font-family: Arial;";
        html += "background: linear-gradient(135deg, #111827, #1f2937);";
        html += "margin: 0;";
        html += "padding: 0;";
        html += "color: #111;";
        html += "}";

        html += ".card {";
        html += "width: 90%;";
        html += "max-width: 1200px;";
        html += "margin: 40px auto;";
        html += "background: #f9fafb;";
        html += "padding: 30px;";
        html += "border-radius: 18px;";
        html += "box-shadow: 0 0 20px rgba(0,0,0,0.4);";
        html += "}";

        html += ".central {";
        html += "text-align: center;";
        html += "max-width: 750px;";
        html += "}";

        html += "h1 {";
        html += "color: #111827;";
        html += "text-align: center;";
        html += "}";

        html += "h2 {";
        html += "color: #1d4ed8;";
        html += "}";

        html += "p {";
        html += "font-size: 16px;";
        html += "color: #374151;";
        html += "}";

        html += ".grid {";
        html += "display: grid;";
        html += "grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));";
        html += "gap: 22px;";
        html += "margin-top: 30px;";
        html += "}";

        html += ".carro-card {";
        html += "background: white;";
        html += "border-radius: 16px;";
        html += "padding: 22px;";
        html += "text-align: center;";
        html += "box-shadow: 0 0 10px rgba(0,0,0,0.15);";
        html += "transition: 0.3s;";
        html += "}";

        html += ".carro-card:hover {";
        html += "transform: scale(1.03);";
        html += "}";

        html += ".figura {";
        html += "font-size: 70px;";
        html += "margin-bottom: 12px;";
        html += "}";

        html += ".grande {";
        html += "font-size: 130px;";
        html += "}";

        html += ".preco {";
        html += "font-size: 20px;";
        html += "font-weight: bold;";
        html += "color: #047857;";
        html += "}";

        html += ".descricao {";
        html += "font-size: 18px;";
        html += "line-height: 1.5;";
        html += "}";

        html += ".botao {";
        html += "display: inline-block;";
        html += "background: #1d4ed8;";
        html += "color: white;";
        html += "padding: 12px 18px;";
        html += "border-radius: 8px;";
        html += "text-decoration: none;";
        html += "font-size: 15px;";
        html += "margin: 5px;";
        html += "}";

        html += ".botao:hover {";
        html += "background: #1e40af;";
        html += "}";

        html += ".secundario {";
        html += "background: #047857;";
        html += "}";

        html += ".secundario:hover {";
        html += "background: #065f46;";
        html += "}";

        html += ".alerta {";
        html += "background: #d97706;";
        html += "}";

        html += ".alerta:hover {";
        html += "background: #b45309;";
        html += "}";

        html += ".perigo {";
        html += "background: #dc2626;";
        html += "}";

        html += ".perigo:hover {";
        html += "background: #991b1b;";
        html += "}";

        html += ".menu-topo {";
        html += "text-align: center;";
        html += "margin-top: 20px;";
        html += "margin-bottom: 20px;";
        html += "}";

        html += ".aviso {";
        html += "background: #fef3c7;";
        html += "border-left: 6px solid #f59e0b;";
        html += "padding: 14px;";
        html += "border-radius: 8px;";
        html += "}";

        html += ".historico {";
        html += "background: white;";
        html += "padding: 25px 40px;";
        html += "border-radius: 12px;";
        html += "box-shadow: inset 0 0 8px rgba(0,0,0,0.1);";
        html += "}";

        html += ".historico li {";
        html += "padding: 10px;";
        html += "font-size: 17px;";
        html += "border-bottom: 1px solid #e5e7eb;";
        html += "}";

        // Fim do CSS.
        html += "</style>";

        // Fecha o cabeçalho.
        html += "</head>";

        // Abre o corpo da página.
        html += "<body>";

        // Retorna o HTML inicial.
        return html;
    }

    // Método que fecha o HTML.
    public static String fimHtml() {

        // Variável usada para montar o fim do HTML.
        String html = "";

        // Fecha o corpo da página.
        html += "</body>";

        // Fecha o documento HTML.
        html += "</html>";

        // Retorna o HTML final.
        return html;
    }
}