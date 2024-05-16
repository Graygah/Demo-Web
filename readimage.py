from PIL import Image
import pytesseract

# Buka gambar
img = Image.open("D:\\Katalon Project\\Demo Web\\Screenshoot\\01.png")

# Gunakan pytesseract untuk melakukan OCR pada gambar
pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'
text = pytesseract.image_to_string(img)

# Tampilkan hasil
print(text)