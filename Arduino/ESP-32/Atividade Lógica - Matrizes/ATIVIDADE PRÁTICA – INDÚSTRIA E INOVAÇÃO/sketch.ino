#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <DHT.h>

// Configurações dos Pinos
#define DHTPIN 2          
#define DHTTYPE DHT22     
#define LED_VERDE 3       
#define LED_AMARELO 4     
#define LED_VERMELHO 5    
#define BUZZER 6          

// Inicialização dos Objetos
DHT dht(DHTPIN, DHTTYPE);
LiquidCrystal_I2C lcd(0x27, 16, 2); 

void setup() {
  // Configuração dos pinos dos atuadores como saída
  pinMode(LED_VERDE, OUTPUT);
  pinMode(LED_AMARELO, OUTPUT);
  pinMode(LED_VERMELHO, OUTPUT);
  pinMode(BUZZER, OUTPUT);

  // Inicialização dos periféricos
  dht.begin();
  lcd.init();
  lcd.backlight();
  
  // Mensagem inicial de boot
  lcd.setCursor(0, 0);
  lcd.print("Sistema Ind. 4.0");
  lcd.setCursor(0, 1);
  lcd.print("Iniciando...");
  delay(2000);
  lcd.clear();
}

void loop() {
  // Leitura da temperatura em Graus Celsius
  float temp = dht.readTemperature();

  // Verifica se a leitura falhou
  if (isnan(temp)) {
    lcd.setCursor(0, 0);
    lcd.print("Erro no Sensor! ");
    return;
  }

  // Exibe a temperatura atual no LCD
  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print(temp, 1);
  lcd.print((char)223); 
  lcd.print("C   ");

  // Tomada de Decisão com base nos dados
  if (temp < 35.0) {
    // SITUAÇÃO 1: Normal
    digitalWrite(LED_VERDE, HIGH);
    digitalWrite(LED_AMARELO, LOW);
    digitalWrite(LED_VERMELHO, LOW);
    noTone(BUZZER);
    
    lcd.setCursor(0, 1);
    lcd.print("Status: NORMAL  ");
    
  } else if (temp >= 35.0 && temp < 50.0) {
    // SITUAÇÃO 2: Atenção
    digitalWrite(LED_VERDE, LOW);
    digitalWrite(LED_AMARELO, HIGH);
    digitalWrite(LED_VERMELHO, LOW);
    noTone(BUZZER);
    
    lcd.setCursor(0, 1);
    lcd.print("Status: ATENCAO ");
    
  } else {
    // SITUAÇÃO 3: Crítico
    digitalWrite(LED_VERDE, LOW);
    digitalWrite(LED_AMARELO, LOW);
    digitalWrite(LED_VERMELHO, HIGH);
    tone(BUZZER, 1000); // Apita a 1000Hz
    
    lcd.setCursor(0, 1);
    lcd.print("Status: CRITICO!");
  }

  delay(1000); 
}