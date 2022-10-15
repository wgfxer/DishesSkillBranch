package ru.skillbranch.sbdelivery

import ru.skillbranch.sbdelivery.data.db.entity.CartItemDbView
import ru.skillbranch.sbdelivery.data.network.res.DishRes
import ru.skillbranch.sbdelivery.data.network.res.ReviewRes
import ru.skillbranch.sbdelivery.data.toCartItem
import ru.skillbranch.sbdelivery.data.toDishContent
import ru.skillbranch.sbdelivery.data.toDishItem
import ru.skillbranch.sbdelivery.data.toDishPersist
import ru.skillbranch.sbdelivery.screens.cart.data.CartItem
import java.util.*

object Stubs {
    val dishesRes : List<DishRes> = listOf(
        DishRes("5ed8da011f071c00465b2026","Бургер \"Америка\"","320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, жареный бекон, сыр чеддер.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372888_m650.jpg",259,170,4f,3,"5ed8da011f071c00465b1fde",8,true, 1591269889000, 1613983220000),
        DishRes("5ed8da011f071c00465b2027","Бургер \"Мексика\"","295 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, мексиканские чипсы начос, лист салата, перчики халапеньо, сыр чеддер, соус сальса из свежих томатов.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372889_m650.jpg",null,259,4f,2,"5ed8da011f071c00465b1fde",3,true, 1591269889000, 1613983220000),
        DishRes("5ed8da011f071c00465b2028","Бургер \"Русский\"","460 г • Две котлеты из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, маринованные огурчики, двойной сыр чеддер, соус ранч.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372890_m650.jpg",null,379,3.75f,1,"5ed8da011f071c00465b1fde",4,true, 1591269889000, 1595588354000),
        DishRes("5ed8da011f071c00465b2029","Бургер \"Люксембург\"","Куриное филе приготовленное на гриле, картофельная булочка на гриле, сыр чеддер, соус ранч, лист салата, томат, свежий огурец.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372891_m650.jpg",null,189,2f,0,"5ed8da011f071c00465b1fde",0,true, 1591269889000, 1603025264000),
        DishRes("5ed8da011f071c00465b202a","Бургер \"Классика\"","290 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, сыр чеддер.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372893_m650.jpg",null,199,0f,0,"5ed8da011f071c00465b1fde",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b202b","Бургер \"Швейцария\"","320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, натуральный сырный соус, лист салата, томат, маринованный огурчик, 2 ломтика сыра чеддер.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312700349_m650.jpg",null,279,0f,0,"5ed8da011f071c00465b1fde",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b202c","Бургер \"Франция\"","305 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, творожный сыр, лист салата, сыр чеддер, карамелизированная груша.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312700351_m650.jpg",null,289,0f,0,"5ed8da011f071c00465b1fde",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b202d","Бургер \"Чак Норрис\"","345 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, соус барбекю, фирменный соус, лист салата, луковые кольца, жареный бекон, сыр чеддер.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312700353_m650.jpg",null,299,0f,0,"5ed8da011f071c00465b1fde",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b202e","Пицца Дьябло с двойной начинкой","Бекон, свинина, пепперони, перец болгарский, халапеньо, томатный пицца-соус, соус шрирача, моцарелла, зелень", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422298_m650.jpg",null,779,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b202f","Пицца Карбонара с двойной начинкой","Бекон, пармезан, соус сливочный, моцарелла", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422305_m650.jpg",null,739,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2030","Пицца Петровская с двойной начинкой","Пепперони, курица, бекон, ветчина, помидоры, шампиньоны, лук красный, моцарелла, укроп", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422296_m650.jpg",null,895,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2031","Пицца 2 берега с двойной начинкой","Свинина, курица, пепперони, ветчина, бекон, помидоры, перец болгарский, соус барбекю, моцарелла,укроп", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422299_m650.jpg",null,899,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2032","Пицца Мясная с двойной начинкой","Охотничьи колбаски, курица, свинина, пепперони, шампиньоны, томатный пицца-соус, моцарелла, зелень", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422306_m650.jpg",null,895,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2033","Пицца Маргарита с двойной начинкой","Моцарелла, помидоры, томатный пицца-соус", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422261_m650.jpg",null,524,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2034","Пицца Марсельеза с двойной начинкой","Грибной жюльен (шампиньоны, лук репчатый, сливки), курица, лук зеленый, моцарелла, пармезан", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422301_m650.jpg",null,799,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2035","Пицца Морская с двойной начинкой","Тигровые креветки, снежный краб, помидоры, соус сливочный, моцарелла, чеснок", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422313_m650.jpg",null,999,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2036","Пицца Пепперони с двойной начинкой","Пепперони, томатный пицца соус, моцарелла", "https://www.delivery-club.ru/media/cms/relation_product/13219/301422300_m650.jpg",692,394,0f,0,"5ed8da011f071c00465b1fe8",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2037","Свекла гриль","250 г • Свекла, масло растительное, бальзамический соус", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156192_m650.jpg",110,88,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da011f071c00465b2038","Картофель отварной с зеленью","200 г • Картофель, зелень, масло сливочное", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156195_m650.jpg",null,75,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269889000, 1591269889000),
        DishRes("5ed8da021f071c00465b2039","Картофельное пюре","200 г • Картофель, молоко, масло сливочное", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156196_m650.jpg",null,90,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203a","Спагетти отварные","200 г • Спагетти, масло сливочное", "https://www.delivery-club.ru/media/cms/relation_product/25057/301630064_m650.jpg",null,75,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203b","Греча отварная","200 г", "https://www.delivery-club.ru/media/cms/relation_product/25057/301630006_m650.jpg",null,75,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203c","Рис","200 г • Рис", "https://www.delivery-club.ru/media/cms/relation_product/25057/301630080_m650.jpg",null,75,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203d","Драники","200 г • Картофель, мука, яйцо", "https://www.delivery-club.ru/media/cms/relation_product/25057/311768055_m650.jpg",null,160,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203e","Кабачковые оладьи","200 г • Кабачок, мука, яйцо", "https://www.delivery-club.ru/media/cms/relation_product/25057/311768058_m650.jpg",null,160,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b203f","Овощи на пару","250 г • Овощное ассорти", "null",170,127,0f,0,"5ed8da011f071c00465b2000",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2040","Ассорти из горячих закусок","450/30/30 г • Наггетсы, чесночные гренки, пельмени жареные, картофель фри, подается с соусом барбекю и чесночным соусом", "null",null,320,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2041","Колбаски фри с чесноком","150 г • Колбаски обжаренные во фритюре", "null",null,210,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2042","Сырные палочки","150/30 г • Подаются с чесночным соусом", "https://www.delivery-club.ru/media/cms/relation_product/36255/315488004_m650.jpg",180,115,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2043","Пивчики","50/30 г • Острые чипсы из лаваша подаются с чесночным соусом", "null",null,70,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2044","Наггетсы","180/30 г • Куриное филе в панировке, обжаренное во фритюре. Подаются с чесночным соусом", "https://www.delivery-club.ru/media/cms/relation_product/36255/315488007_m650.jpg",null,210,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2045","Уши свиные к пиву","150/30/5 г • Свиные уши, маринованные в соевом соусе с чесноком и обжаренные во фритюре", "https://www.delivery-club.ru/media/cms/relation_product/36255/315488008_m650.jpg",null,185,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2046","Мойва к пиву","200/30 г • Мойва с чесноком и соевым соусом, обжаренная во фритюре. Подаются с чесночным соусом", "https://www.delivery-club.ru/media/cms/relation_product/36255/315488009_m650.jpg",null,165,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2047","Кальмары в кляре","150/30 г • Кольца кальмаров, обжаренные во фритюре, подаются с острым соусом", "null",null,215,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2048","Острые куриные сердечки","150/30 г • Куриные сердечки обжаренные во фритюре. Подаются с соусом аджика", "https://www.delivery-club.ru/media/cms/relation_product/36255/315488011_m650.jpg",null,215,0f,0,"5ed8da011f071c00465b201a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2049","Огурчики с/с с чесноком","150 г", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487984_m650.jpg",null,110,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204a","Семга \"Шеф-посол\"","100 г", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487986_m650.jpg",null,290,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204b","Селедочка под водочку","100/60/40 г • Сельдь с/с с картофелем и маринованным лучком", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487987_m650.jpg",null,140,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204c","Язык говяжий","100 г • Отварной язык. Подается с хреном и горчицей", "null",null,240,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204d","Ассорти рыбное","250 г • Кета х/к, скумбрия х/к, семга с/с. Подается с лимоном, маслинами, оливками", "null",null,430,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204e","Ассорти мясное","200/30 г • Ростбиф маринованный, язык говяжий, колбаса вяленая, карбонад копченый, сало соленое. Подается с хреном и горчицей", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487991_m650.jpg",null,330,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b204f","Паштет из куриной печени","100/60 г • Подается с гренками", "null",null,160,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2050","Ассорти \"На грядке\"","200 г • Огурцы, томаты, перец болгарский, редис, салат, укроп, петрушка", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487993_m650.jpg",null,170,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2051","Грузди маринованные","100/25/25 г • Подаются со сметаной и репчатым луком", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487994_m650.jpg",280,212,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2052","Закуска «Погребок»","250/10 г • Огурцы с/с, корнишоны, грибы маринованные, корейская морковка, салат капустный", "null",190,146,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2053","Сало соленое с гренками","150/60/30 г • Подается с гренками и горчицей", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487996_m650.jpg",null,190,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2054","Сырная тарелка","120/20/30 г • Сыр маасдам, сыр с плесенью, сыр тилизитер, крекер, мед, яблоко, миндаль", "https://www.delivery-club.ru/media/cms/relation_product/36255/315487997_m650.jpg",null,270,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2055","Лимон","50 г", "null",null,35,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2056","Оливки","50 г", "null",100,88,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2057","Маслины","50 г", "null",null,100,0f,0,"5ed8da011f071c00465b2018",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2058","Хот-дог \"Нью-Йорк\"","250 г • Домашняя колбаска на гриле, хрустящий багет, лист салата, маринованная капуста, жареный бекон, фирменный соус, кетчуп.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372894_m650.jpg",null,169,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2059","Хот-дог \"Женева\"","240 г • Домашняя колбаска на гриле, хрустящий багет, лист салата, томат, сырный соус, маринованный огурчик.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372895_m650.jpg",null,179,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205a","Хот-дог \"Классика\"","225 г • Домашняя колбаска на гриле, хрустящий багет, салат айсберг, томат, фирменный соус.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312372896_m650.jpg",null,139,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205b","Хот-дог \"Сантьяго\"","240 г • Домашняя колбаска на гриле, хрустящий багет, лист салата, перчики халапеньо, соус сальса из свежих томатов, фирменный соус, мексиканские чипсы начос.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312718514_m650.jpg",null,169,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205c","Хот-дог \"Королина\"","235 г • Домашняя колбаска на гриле, хрустящий багет, лист салата, жареный бекон, соус барбекю, фирменный соус, хрустящий лучок.", "https://www.delivery-club.ru/media/cms/relation_product/32350/312719020_m650.jpg",null,179,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205d","Хот-Дог \"Даллас\"","240 г • Домашняя колбаска на гриле, хрустящий багет, салат айсберг, томат, свежий огурчик, соус ранч.", "https://www.delivery-club.ru/media/cms/relation_product/32350/316457706_m650.jpg",null,189,0f,0,"5ed8da011f071c00465b1fe0",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205e","Пирог с сельдью, классический","Сельдь, картофель, репчатый лук, зелень, фирменный соус.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025718_m650.jpg",null,540,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b205f","Пирог с рыбой, классический","Треска, репчатый лук, зелень, фирменный соус.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025719_m650.jpg",null,540,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2060","Пирог с мясом, классический","Свежий фарш (говядина), репчатый лук (жареный), яйцо, томаты, немного соуса.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025720_m650.jpg",null,540,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2061","Пирог c томатами, классический","Пирог с томатами, яйцом, двумя видами сыра и зеленью.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025721_m650.jpg",null,450,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2062","Пирог c куриным филе, классический","Пирог с куриным филе, творогом, томатами и зеленью.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025722_m650.jpg",null,540,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2063","Пирог c капустой, классический","Пирог с капустой, зеленым луком и яйцом.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025723_m650.jpg",null,390,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2064","Пирог c ветчиной, классический","Пирог с ветчиной, картофелем, зеленью и сыром.", "https://www.delivery-club.ru/media/cms/relation_product/520/317025724_m650.jpg",null,540,0f,0,"5ed8da011f071c00465b200a",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2065","Сет к пиву","800 г • Тигровые креветки по-тайски, 2 порции куриных крылышек в соусе \"Кимчи\" и хрустящий кальмар.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315108129_m650.jpg",null,1350,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2066","Тайский сет","1270 г • 2 супа том ям с тигровой креветкой, лапша пад тай с курицей и креветками, тайский рис с курицей и креветками, бананы по-тайски.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315108130_m650.jpg",null,1490,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2067","Комбо на компанию из 6 человек","2490 г • 3 порции лапши пад тай с курицей и креветками, 3 порции лапши удон с курицей в соусе \"Стирфрай\", 3 порции куриных крылышек в соусе \"Кимчи\".", "https://www.delivery-club.ru/media/cms/relation_product/30136/315108131_m650.jpg",null,2800,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2068","Сет \"№1\" 22 шт","570 г • Суши с лососем 2 шт ролл с тигровой креветкой 6 шт ролл с тунцом 6 шт ролл бонито 8 шт.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315602899_m650.jpg",890,845,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2069","Сет \"№2\" 20шт","550 г • Ролл пиро с лососем 8 шт ролл лава 8 шт спайси суши с лососем 2 шт спайси суши с тунцом 2 шт.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315602900_m650.jpg",null,990,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206a","Сет \"№3\" 34шт","930 г • Суши с угрем 2 шт спайси суши с тунцом 2 шт ролл окинава 8 шт ролл фудзи 8 шт ролл эби тобико 8 шт ролл с лососем 6 шт.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315602901_m650.jpg",null,1490,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206b","Сет \"№4\" 48шт","1170 г • Суши с тунцом 4 шт ролл с лососем 6 шт ролл филадельфия с огурцом 8 шт ролл эби тобико 8 шт ролл самбай 8 шт ролл лава 8 шт ролл с авокадо 6 шт.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315602902_m650.jpg",null,2090,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206c","Сет \"№5\" 56шт","1340 г • Ролл с лососем 6 шт ролл с угрем 6 шт ролл с тигровой креветкой 6 шт ролл филадельфия 8 шт ролл сакура татаки 8 шт ролл зодиак 8 шт ролл хоккайдо 8 шт спайси суши с лососем 2 шт спайси суши с креветкой 2 шт спайси суши с тунцом 2 шт.", "https://www.delivery-club.ru/media/cms/relation_product/30136/315602903_m650.jpg",null,2690,0f,0,"5ed8da011f071c00465b1ff2",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206d","Слойка с курицей конвертик 75 г","75 г • Прослоенное дрожжевое тесто, приготовленное по секретной технологии сибирских умельцев, с начинкой из филе курицы и обжаренного лука", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929173_m650.jpg",null,42,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206e","Слойка с картофелем и грибами 75 г","75 г • Дрожжевое слоеное тесто из картофеля и шампиньонов, лук", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929176_m650.jpg",null,35,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b206f","Пирожок печеный с капустой 70 г","70 г • Дрожжевое тесто с капустой и луком", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929197_m650.jpg",null,23,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2070","Пирожок печеный с картофелем 70 г","70 г • Дрожжевое тесто с начинкой из картофеля, лука", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929198_m650.jpg",null,23,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2071","Пирожок с зеленым луком и яйцом 80 г","80 г • Дрожжевое тесто с начинкой из зеленого лука и отварного яйца", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929200_m650.jpg",null,45,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2072","Пирожок печеный с картофелем и опятами 70 г","70 г • Дрожжевое тесто с начинкой из картофеля, опят и лука", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929199_m650.jpg",null,25,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2073","Сочень с творогом 90 г","90 г • песочное тесто с творожной начинкой", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929180_m650.jpg",null,49,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2074","Сочень c яблоком и корицей","95 г • Песочное тесто с начинкой из яблок и корицы", "https://www.delivery-club.ru/media/cms/relation_product/31898/314059397_m650.jpg",null,49,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2075","Штрудель с яблоком","Австрийская выпечка из тонкого слоеного теста с яблочной начинкой с добавлением корицы. Порция на выбор: 200 г или 1000 г", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929189_m650.jpg",null,128,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2076","Штрудель с вишней","Австрийская выпечка из тонкого слоеного теста с вишнёвой начинкой. Порция на выбор: 200 г или 850 г", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929190_m650.jpg",null,160,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2077","Круассан классический 85 г","85 г • Дрожжевое слоеное тесто", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929202_m650.jpg",null,57,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2078","Круассан с шоколадом 110 г","110 г • Дрожжевое слоеное тесто с начинкой из шоколада", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929203_m650.jpg",null,69,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2079","Круассан с сыром и корицей 100 г","100 г • Дрожжевое слоеное тесто с начинкой из заварного крема патисьер и мягкого сливочного сыра", "https://www.delivery-club.ru/media/cms/relation_product/31898/311929204_m650.jpg",null,59,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207a","Крутяш с корицей","Сдобно-дрожжевое тесто с начинкой из молотой корицы и сверху полит сливочно-сырным кремом", "null",null,49,0f,0,"5ed8da011f071c00465b2010",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207b","Шницель свиной по-министерски","200 г • Свиная корейка, панировочные сухари, яйцо куриное", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156187_m650.jpg",null,200,0f,0,"5ed8da011f071c00465b1ffe",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207c","Свиная вырезка, запечённая в сырной корочке","250 г • Свиная корейка, сыр твердый, яйцо", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156188_m650.jpg",200,116,0f,0,"5ed8da011f071c00465b1ffe",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207d","Эскалоп из свинины жареный с луком фри","200 г • Свиная корейка, лук репчатый", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156189_m650.jpg",null,200,0f,0,"5ed8da011f071c00465b1ffe",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207e","Котлета \"Домашняя\" жареная","180 г • Свинина, говядина, яйцо куриное", "https://www.delivery-club.ru/media/cms/relation_product/25057/308156190_m650.jpg",null,160,0f,0,"5ed8da011f071c00465b1ffe",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b207f","Свинина по-французки","200 г • Свинина карбонат, грибы шампиньоны, лук, сыр, майонез, специи, соль", "null",null,200,0f,0,"5ed8da011f071c00465b1ffe",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2080","Бон аква газированная","330 мл • Минеральная вода, негазированная.", "https://www.delivery-club.ru/media/cms/relation_product/26016/315641980_m650.jpg",null,159,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2081","Бон аква негазированная","330 мл • Минеральная вода, негазированная.", "https://www.delivery-club.ru/media/cms/relation_product/26016/315641981_m650.jpg",null,159,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2082","Лимонад крем сода","500 мл • Лимонад с нежным сливочным вкусом, который образуется благодаря сочетанию апельсина, лимона, содовой и ванильно-карамельного сиропа.", "https://www.delivery-club.ru/media/cms/relation_product/26016/310671145_m650.jpg",null,199,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2083","Лимонад тархун","500 мл • Пряный тархун в сочетании с лимонным и яблочным соками и газированной водой.", "https://www.delivery-club.ru/media/cms/relation_product/26016/310671146_m650.jpg",null,199,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2084","Стелла артуа БЕЗАЛКОГОЛЬНОЕ","500 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/315596369_m650.jpg",null,299,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2085","Сок апельсиновый","200 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/315596370_m650.jpg",null,135,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2086","Сок вишневый","200 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/315596371_m650.jpg",null,135,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2087","Сок персиковый","200 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/31596372_m650.jpg",135,85,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2088","Сок томатный","200 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/315596373_m650.jpg",null,135,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000),
        DishRes("5ed8da021f071c00465b2089","Сок яблочный","200 мл", "https://www.delivery-club.ru/media/cms/relation_product/26016/315596374_m650.jpg",135,76,0f,0,"5ed8da011f071c00465b2024",0,true, 1591269890000, 1591269890000)
    )

    val dishesPersist = dishesRes.map { it.toDishPersist() }

    val dishItems = dishesPersist.map { it.toDishItem() }

    val searchResult = dishesRes.filter { it.name.contains("Бургер") }
        .map { it.toDishPersist() }

    val searchResultItems = searchResult
        .map { it.toDishItem() }

    val dishPersist = dishesPersist.first()
    val dishContent = dishPersist.toDishContent()

    val comments = listOf<ReviewRes>(
        ReviewRes("Elena", 1618063000, 5, "test"),
        ReviewRes("Pavel", 1618068000, 4, "test2"),
        ReviewRes("Petr", 1618075000, 2, "test3")
    )

    val cartPersistItems : List<CartItemDbView> = listOf(
        CartItemDbView("test1", "anyUrl", "test1", 10, 100),
        CartItemDbView("test2","anyUrl", "test2", 20, 200),
        CartItemDbView("test3","anyUrl", "test3", 30, 300)
    )

    val cartItems : List<CartItem> = cartPersistItems.map { it.toCartItem() }
}
