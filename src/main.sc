require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        image: https://allfest.ru/sites/default/files/styles/af_1200x798/public/2023-09/dvizh-fest.jpg?itok=aJKNaHii
        a: Привет! Надеюсь, ты ознакомился с информацией о наших компаниях. Осталось проверить, насколько хорошо ты всё запомнил: отвечай на вопросы и получи наклейку в свой паспорт участника.|| htmlEnabled = true, html = "<b>Привет!</b> <br><br>Надеюсь, ты ознакомился с <b>информацией о наших компаниях</b>. Осталось проверить, насколько хорошо ты всё запомнил: <b>отвечай на вопросы и получи наклейку в свой паспорт участника.</b>"
        buttons:
            "Старт" -> /Старт
        intent: /Статус заказа || onlyThisState = false, toState = "/Статус заказа"
        intent: /sys/ru/aimylogic/parting || onlyThisState = false, toState = "/Bye"

    state: Bye
        a: Пока-пока!
        EndSession:
            actions = {}

