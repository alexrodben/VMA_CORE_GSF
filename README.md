# VMA_CORE_GSF

Librería Android pensada para **proyectos sin Google Services Framework (GSF)**, como tablets industriales, vending machines o dispositivos AOSP. Permite reutilizar componentes core comunes sin depender de Google Play Services.

---

## 📦 Dependencia

```kotlin
implementation("com.github.alexrodben:VMA_CORE_GSF:v1.0.0")
```

---

## ✅ Requisitos

Antes de instalar la librería, asegúrate de cumplir lo siguiente:

* **Android Studio Flamingo o superior**
* **Gradle 8+**
* **Kotlin 1.9+**
* **minSdk recomendado:** 21
* Proyecto **SIN Google Play Services** (compatible con AOSP)

---

## 🔧 Paso 1: Habilitar JitPack

La librería se distribuye mediante **JitPack**, por lo que debes agregar su repositorio.

### Opción A: `settings.gradle.kts` (RECOMENDADO)

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Opción B: `build.gradle` (proyectos antiguos)

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

---

## 📥 Paso 2: Agregar la dependencia

En el archivo **`app/build.gradle.kts`**:

```kotlin
dependencies {
    implementation("com.github.alexrodben:VMA_CORE_GSF:v1.0.0")
}
```

Luego sincroniza el proyecto:

```
File > Sync Project with Gradle Files
```

---

## 🧩 Paso 3: Configuración de Kotlin (recomendado)

Asegúrate de tener configurado el `jvmTarget` correctamente:

```kotlin
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}
```

---

## 🚀 Uso básico

Una vez instalada, puedes importar los módulos expuestos por la librería:

```kotlin
import com.vma.core_gsf.*
```

> 📌 Consulta la documentación interna o el código fuente para ver los componentes disponibles.

---

## 🧪 Compatibilidad probada

* ✅ Android 7.1.2 (API 25)
* ✅ Tablets industriales
* ✅ Vending machines
* ❌ No requiere Google Play Services

---

## 🛠 Problemas comunes

### ❗ Error: `Could not find com.github.alexrodben`

* Verifica que **JitPack esté correctamente agregado**
* Asegúrate de estar usando la versión correcta: `v1.0.0`
* Limpia y reconstruye el proyecto:

```
./gradlew clean build
```

---

## 📄 Licencia

MIT License

---

## 👤 Autor

**Alex Rodríguez**
GitHub: [https://github.com/alexrodben](https://github.com/alexrodben)

---

## ⭐ Soporte

Si esta librería te fue útil, considera darle una ⭐ en GitHub o abrir un issue para mejoras.

---

> Diseñada para entornos embebidos, AOSP y soluciones industriales sin dependencia de Google.
