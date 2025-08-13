require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Старт || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        image: https://allfest.ru/sites/default/files/styles/af_1200x798/public/2023-09/dvizh-fest.jpg?itok=aJKNaHii
        a: Привет! Надеюсь, ты ознакомился с информацией о наших компаниях. Осталось проверить, насколько хорошо ты всё запомнил: отвечай на вопросы и получи наклейку в свой паспорт участника. || htmlEnabled = true, html = "<b>Привет!</b> <br><br>Надеюсь, ты ознакомился с <b>информацией о наших компаниях</b>. Осталось проверить, насколько хорошо ты всё запомнил: <b>отвечай на вопросы и получи наклейку в свой паспорт участника.</b>"
        buttons:
            "Старт" -> /Ввод ФИО
        intent: /Старт || onlyThisState = false, toState = "/Старт"
        intent: /sys/ru/aimylogic/parting || onlyThisState = false, toState = "/Bye"

    state: Ввод ФИО || sessionResult = "Старт", sessionResultColor = "#7E47D1"
        InputText: 
            prompt = Скажите, пожалуйста, ваше полное ФИО?
            varName = userFullName
            html = 
            htmlEnabled = false
            actions = 
            then = /ФИО сохранено
        intent: /ФИО || onlyThisState = false, toState = "/ФИО сохранено"
        event: noMatch || onlyThisState = false, toState = "/Неправильный ввод"
    
    state: ФИО сохранено
        a: Отлично! А теперь подскажи, в какой компании ты работаешь? Выбери из предложенных вариантов. Если ты пришёл поддержать своего близкого человека, выбирай кнопку «гость». || htmlEnabled = true, html = "<b>Отлично!</b> <br><br>А теперь подскажи, <b>в какой компании ты работаешь?</b> Выбери из предложенных вариантов. Если ты пришёл поддержать своего близкого человека, выбирай кнопку <b>«гость»</b>"
        buttons:
            "СУЭК" -> /Старт теста
            "СГК" -> /Старт теста
            "ЕвроХим" -> /Старт теста
            "ГРК" -> /Старт теста
            "НТК" -> /Старт теста
            "Портовый Альянс" -> /Старт теста
            "ЦТИП" -> /Старт теста
            "ФМ" -> /Старт теста
        buttons:
            "Гость" -> /Старт теста

    state: Старт теста
        a: Отлично! Дело за малым: жми кнопку «Готов», пройди тест и проверь себя. Удачи! || htmlEnabled = true, html = "<b>Отлично!</b> <br><br>Дело за малым: <b>жми кнопку «Готов»</b>, пройди тест и проверь себя. <br><br><b>Удачи!</b>"
        buttons:
            "Готов"
    
    state: Bye
        a: Пока-пока!
        EndSession: 
            actions = {}