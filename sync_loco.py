import urllib.request
import zipfile
import io
import os
import shutil

def get_api_key():
    # Intenta leer de local.properties
    props_path = "local.properties"
    api_key = "E4Z0613eZP6ChsehtoNPQioqGapry8c7" # Default (Key proporcionada)
    
    if os.path.exists(props_path):
        with open(props_path, "r", encoding="utf-8") as f:
            for line in f:
                if "=" in line and not line.strip().startswith("#"):
                    key, val = line.strip().split("=", 1)
                    if key.strip() == "loco_key":
                        api_key = val.strip()
                        break
    return api_key

def clean_old_loco_files(base_path):
    print("Limpiando traducciones antiguas de Loco...")
    if not os.path.exists(base_path):
        return
        
    for root, dirs, files in os.walk(base_path):
        for file in files:
            if file == "loco_strings.xml":
                old_file = os.path.join(root, file)
                try:
                    os.remove(old_file)
                    print(f"  -> Eliminado: {old_file}")
                except Exception as e:
                    print(f"  -> Error al eliminar {old_file}: {e}")

def sync_loco_translations():
    api_key = get_api_key()
    print("Iniciando conexión con Localise.biz (Loco)...")
    url = f"https://localise.biz/api/export/archive/xml.zip?format=android&key={api_key}"
    
    base_path = os.path.join("composeApp", "src", "commonMain", "composeResources")
    
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as response:
            zip_content = response.read()
            
        print("Traducciones descargadas. Procesando...")
        
        # Elimina las versiones viejas de loco_strings.xml primero para asegurar actualización
        clean_old_loco_files(base_path)
        
        os.makedirs(base_path, exist_ok=True)
        
        with zipfile.ZipFile(io.BytesIO(zip_content)) as z:
            for file_info in z.infolist():
                filename = file_info.filename
                
                if "values" in filename and filename.endswith(".xml"):
                    parts = filename.split("/")
                    values_dir = None
                    xml_name = "loco_strings.xml"
                    
                    for i, part in enumerate(parts):
                        if part.startswith("values"):
                            values_dir = part
                            break
                    
                    if values_dir:
                        dest_dir = os.path.join(base_path, values_dir)
                        os.makedirs(dest_dir, exist_ok=True)
                        dest_path = os.path.join(dest_dir, xml_name)
                        
                        with z.open(file_info) as source, open(dest_path, "wb") as target:
                            shutil.copyfileobj(source, target)
                        print(f"[OK] Sincronizado: {dest_path}")
                        
        print("\n¡Traducciones actualizadas correctamente! Recompila tu app de Android.")
    except Exception as e:
        print(f"\n[ERROR] Hubo un problema al sincronizar: {e}")

if __name__ == "__main__":
    sync_loco_translations()
