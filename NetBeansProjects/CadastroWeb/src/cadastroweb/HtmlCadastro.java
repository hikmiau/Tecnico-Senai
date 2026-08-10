package cadastroweb;

public class HtmlCadastro {

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
        html += "background: linear-gradient(135deg, #0f172a, #1e293b);";
        html += "margin: 0;";
        html += "padding: 0;";
        html += "}";

        html += ".card {";
        html += "width: 90%;";
        html += "max-width: 1100px;";
        html += "background: #f8fafc;";
        html += "margin: 40px auto;";
        html += "padding: 30px;";
        html += "border-radius: 16px;";
        html += "box-shadow: 0 0 20px rgba(0,0,0,0.35);";
        html += "}";

        html += ".central {";
        html += "max-width: 700px;";
        html += "text-align: center;";
        html += "}";

        html += "h1 {";
        html += "color: #0f172a;";
        html += "text-align: center;";
        html += "}";

        html += "p {";
        html += "color: #334155;";
        html += "font-size: 16px;";
        html += "}";

        html += "label {";
        html += "display: block;";
        html += "margin-top: 12px;";
        html += "font-weight: bold;";
        html += "color: #1e3a8a;";
        html += "}";

        html += "input, select {";
        html += "width: 100%;";
        html += "padding: 10px;";
        html += "margin-top: 5px;";
        html += "border-radius: 6px;";
        html += "border: 1px solid #93c5fd;";
        html += "font-size: 15px;";
        html += "}";

        html += ".menu {";
        html += "display: flex;";
        html += "gap: 12px;";
        html += "justify-content: center;";
        html += "flex-wrap: wrap;";
        html += "margin-top: 25px;";
        html += "}";

        html += ".botao {";
        html += "background: #2563eb;";
        html += "color: white;";
        html += "padding: 11px 18px;";
        html += "border-radius: 8px;";
        html += "text-decoration: none;";
        html += "border: none;";
        html += "cursor: pointer;";
        html += "font-size: 15px;";
        html += "}";

        html += ".botao:hover {";
        html += "background: #1d4ed8;";
        html += "}";

        html += ".secundario {";
        html += "background: #059669;";
        html += "}";

        html += ".perigo {";
        html += "background: #dc2626;";
        html += "}";

        html += ".aviso {";
        html += "background: #fef3c7;";
        html += "border-left: 6px solid #f59e0b;";
        html += "padding: 12px;";
        html += "border-radius: 8px;";
        html += "}";

        html += ".busca {";
        html += "display: flex;";
        html += "gap: 10px;";
        html += "margin: 20px 0;";
        html += "}";

        html += ".busca input {";
        html += "flex: 1;";
        html += "}";

        html += "table {";
        html += "width: 100%;";
        html += "border-collapse: collapse;";
        html += "margin-top: 20px;";
        html += "}";

        html += "th {";
        html += "background: #1e3a8a;";
        html += "color: white;";
        html += "padding: 10px;";
        html += "}";

        html += "td {";
        html += "border: 1px solid #bfdbfe;";
        html += "padding: 9px;";
        html += "text-align: center;";
        html += "}";

        html += "tr:nth-child(even) {";
        html += "background: #dbeafe;";
        html += "}";

        html += ".mini {";
        html += "padding: 7px 10px;";
        html += "border-radius: 6px;";
        html += "text-decoration: none;";
        html += "color: white;";
        html += "margin: 3px;";
        html += "display: inline-block;";
        html += "}";

        html += ".editar {";
        html += "background: #f59e0b;";
        html += "}";

        html += ".excluir {";
        html += "background: #dc2626;";
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