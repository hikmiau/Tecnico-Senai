public class HtmlUtil {

    public static String inicioHtml(String titulo) {

        String html = "";

        html += "<!DOCTYPE html>";
        html += "<html lang='pt-br'>";
        html += "<head>";
        html += "<meta charset='UTF-8'>";
        html += "<title>" + titulo + "</title>";

        html += "<style>";

        html += "body {";
        html += "font-family: Arial;";
        html += "background: linear-gradient(135deg, #e3f2fd, #ffffff);";
        html += "margin: 0;";
        html += "padding: 0;";
        html += "}";

        html += ".card {";
        html += "width: 90%;";
        html += "max-width: 1200px;";
        html += "margin: 40px auto;";
        html += "background: white;";
        html += "padding: 30px;";
        html += "border-radius: 14px;";
        html += "box-shadow: 0 0 15px #b0bec5;";
        html += "}";

        html += ".central {";
        html += "text-align: center;";
        html += "max-width: 700px;";
        html += "}";

        html += "h1 {";
        html += "color: #0D47A1;";
        html += "}";

        html += "h2 {";
        html += "color: #1565C0;";
        html += "margin-top: 25px;";
        html += "}";

        html += "p {";
        html += "font-size: 16px;";
        html += "color: #333;";
        html += "}";

        html += "label {";
        html += "display: block;";
        html += "margin-top: 12px;";
        html += "font-weight: bold;";
        html += "color: #0D47A1;";
        html += "}";

        html += "input, textarea {";
        html += "width: 100%;";
        html += "padding: 10px;";
        html += "margin-top: 5px;";
        html += "border: 1px solid #90CAF9;";
        html += "border-radius: 6px;";
        html += "font-size: 15px;";
        html += "}";

        html += "textarea {";
        html += "height: 80px;";
        html += "}";

        html += ".menu {";
        html += "margin-top: 25px;";
        html += "display: flex;";
        html += "gap: 15px;";
        html += "justify-content: center;";
        html += "flex-wrap: wrap;";
        html += "}";

        html += ".botao {";
        html += "background: #0D47A1;";
        html += "color: white;";
        html += "padding: 12px 20px;";
        html += "border-radius: 8px;";
        html += "text-decoration: none;";
        html += "border: none;";
        html += "font-size: 15px;";
        html += "cursor: pointer;";
        html += "}";

        html += ".botao:hover {";
        html += "background: #08306b;";
        html += "}";

        html += ".secundario {";
        html += "background: #00897B;";
        html += "}";

        html += ".secundario:hover {";
        html += "background: #00695C;";
        html += "}";

        html += ".perigo {";
        html += "background: #C62828;";
        html += "}";

        html += ".perigo:hover {";
        html += "background: #8E0000;";
        html += "}";

        html += ".aviso {";
        html += "background: #FFF3CD;";
        html += "border-left: 6px solid #FFB300;";
        html += "padding: 12px;";
        html += "border-radius: 6px;";
        html += "}";

        html += "table {";
        html += "width: 100%;";
        html += "border-collapse: collapse;";
        html += "margin-top: 20px;";
        html += "}";

        html += "th {";
        html += "background: #0D47A1;";
        html += "color: white;";
        html += "padding: 10px;";
        html += "}";

        html += "td {";
        html += "border: 1px solid #BBDEFB;";
        html += "padding: 8px;";
        html += "text-align: center;";
        html += "}";

        html += "tr:nth-child(even) {";
        html += "background: #E3F2FD;";
        html += "}";

        html += "</style>";

        html += "</head>";
        html += "<body>";

        return html;
    }

    public static String fimHtml() {

        String html = "";

        html += "</body>";
        html += "</html>";

        return html;
    }
}