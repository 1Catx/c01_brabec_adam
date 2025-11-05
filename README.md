Rasterizace úsečky, tečkované čáry a n-úhelníku

Vytvořte program pro kreslení úsečky zadané dvěma libovolnými koncovými body [x1,y1] a [x2,y2].
Koncové body zadávejte interaktivně pomocí tzv. pružné čáry. Stisknutím tlačítka myši zadáte první vrchol, při tažení myší se bude vykreslovat aktuální úsečka společně s již vykreslenou scénou a při uvolnění tlačítka se potvrdí koncový vrchol.
K implementaci použijte rozhraní a třídy definované na cvičeních. Třídy případně upravte nebo doplňte o potřebné metody. Návrhy třídy Point, Line a LineRasterizer naleznete v modulu task1 (viz Oliva-obsah-ukázky a návody).
Vytvořte třídu FilledLineRasterizer dědící z abstraktní třídy LineRasterizer a správně implementující libovolný algoritmus pro rasterizaci úsečky. Do komentáře zapište, o jaký algoritmus se jedná, jeho výhody a nevýhody, případně jiná jeho specifika.
Prozkoumejte třídy Canvas, CanvasKey, CanvasPaint, …, CanvasRasterBufferedImage (v BB ukázky a návody), řešící hlavní aplikační třídu a UI. Soustřeďte se na interface Raster a třídu RasterBufferedImage a použijte ji pro vaše řešení.
Třídy zakomponujte do aplikace, která bude interaktivně zadávat body tvořící vrcholy n-úhelníku. Využijte ukázku CanvasRasterBufferedImage.
Zkušenější studenti mohou použít aplikační logiku z modulu task2, primárně určeného pro druhou úlohu. Tyto ukázky aplikačního řešení nejsou dogma, můžete je modifikovat nebo navrhnout vlastní. Snažte se o rozdělení aplikace na smysluplné třídy a zachovejte koncept rozhraní a tříd Raster, Point, Line a LineRasterizer.
Vytvořte si vhodnou třídu Polygon pro ukládání vrcholů. Je vhodnější ukládat vrcholy ne hrany z důvodu zajištění uzavřenosti útvaru, viz druhá úloha.
Vrcholy zadávejte interaktivně: stisknutím tlačítka myši vytvořte nový bod spojený s dvěma již vytvořenými vrcholy, např. s prvním a posledním. Tažením kreslete pružnou čáru k oběma vrcholům a uvolněním tlačítka přidejte bod do seznamu vrcholů n-úhelníku.
Přidejte řežim (po stisku klávesy Shift) pro kreslení vodorovných, svislých a úhlopříčných úseček. První bod je zadán stiskem, druhý určen na základě polohy při tažení myši, tak aby se vybral nejbližší koncový bod z možné vodorovné, svislé nebo úhlopříčné úsečky.
Implementujte algoritmus vykreslující úsečku s barevným přechodem mezi dvěma koncovými barevnými odstíny. 
Implementujte funkci na klávesu C pro mazání plátna a všech datových struktur.
