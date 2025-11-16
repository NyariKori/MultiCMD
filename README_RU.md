# MultiCMD

[![English](https://img.shields.io/badge/Language-English-blue)](README.md)
[![Russian](https://img.shields.io/badge/Language-Русский-red)](README_RU.md)

MultiCMD — это мощный и легкий плагин для серверов Paper/Spigot/Bukkit, который позволяет выполнять несколько команд в одной строке с помощью разделителя `&`.

### Возможности
- **Цепочки команд:** Объединяйте несколько команд в одну для эффективного выполнения
- **Универсальная совместимость:** Работает со всеми ванильными командами и большинством команд других плагинов
- **Простой синтаксис:** Простая структура использования — просто разделяйте команды символом `&`
- **Мощная автоматизация:** Идеально для быстрых наборов, последовательностей телепортаций и автоматических модерационных действий

### Использование
`/test1 & ban player1 'Was behaving poorly'`

`/gamemode creative & fly on & heal & feed`

`/spawn & time set day & weather clear`

`/vip & eco give %player% 1000 & broadcast New VIP %player% joined!`

### Права
- `multicmd.use` - Право на использование плагина **MultiCMD**
- `multicmd.reload` - Право на использование команды `/multicmd reload`

### Команды
- `/multicmd reload` - Перезагрузить конфигурацию плагина

### Совместимость
- **Paper 1.20.1+**