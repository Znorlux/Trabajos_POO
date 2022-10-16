import java.util.Scanner;
import javax.xml.transform.Templates;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors; 

public class Main{
    public static Scanner palabra_ingresada = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m"; 
    public static final String ANSI_RESET = "\u001B[0m"; 
    public static final String ANSI_YELLOW = "\u001B[33m"; //Letra correcta pero posición incorrecta
    public static final String ANSI_GREEN = "\u001B[32m"; //Letra correcta

    public static void main(String[] args) {
        //Definicion de variables
        long startTime = System.currentTimeMillis();
        int attempts = 0; //Intentos
        System.out.println("Bienvenido a Wordle! \nEscribe una palabra de 5 letras:");
        String answerChoosen = ReturnRandomWord(); //Obtenemos la palabra que se usará en el juego, desde el metodo ReturnRandomWord
        char[] answer; //La respuesta luego será un CharArray 
        char[] palabra; //El input luego será un CharArray   
        boolean done = false;
        String input_palabra = "";
        
        //Ciclo principal
        while (!done){
            System.out.println("==================");
            input_palabra = palabra_ingresada.nextLine().toLowerCase(); //Leemos y guardamos la palabra ingresada por el usuario y la ponemos en minuscula
            while(input_palabra.length() !=5){ //Si la palabra no tiene 5 letras, entonces no se le permitirá continuar, y su intento no sumará
                System.out.println("Debes ingresar una palabra de 5 letras, vuelve a intentarlo");
                input_palabra = palabra_ingresada.nextLine().toLowerCase();
            }

            attempts++; //Los intentos se suman cada vez que se repita el ciclo
            palabra = input_palabra.toCharArray(); //Asignamos el input a un arreglo de caracteres, de "hola" pasa a ["h","o","l","a"]
            answer = answerChoosen.toCharArray(); //la respuesta tambien será un CharArray
            if (PrintWordWithColor(palabra, answer, attempts)) done = true; //Llamamos al metodo PrintWithColor para hacer las comparaciones y pintar las letras

            System.out.println("Llevas "+attempts +" intentos");
        }
        
        System.out.println("Encontraste la palabra correcta, felicidades!, lo hiciste en " + ((System.currentTimeMillis() - startTime) / 1000) + " segundos");

    }

    public static boolean PrintWordWithColor(char[] palabraWord, char[] correctWord, int attempts_max) {
        boolean correct = true;
        char[] answerTemp = correctWord; //Se asigna de manera temporal la palabra correcta, ya como un CharArray
        int[] colorForLetter = new int[5]; //0 es gris, amarillo es 1, verde es 2
        int attempts_end = attempts_max;

        //Comparaciones y coloreo de letras
        for (int i = 0; i < 5; i++) { //Prueba buscar letra que esté en la palabra y que esté en su posición correspondiente (verde)
            if (palabraWord[i] == answerTemp[i]) { //Lee el indice de la palabra del usuario y lo compara con los indices de la respuesta
                answerTemp[i] = '-'; 
                colorForLetter[i] = 2; //Si las letras de la palabra son iguales a la respuesta, el color de la letra sera 2, es decir, verde
            } else correct = false;
        }

        for (int j = 0; j < 5; j++) { //Prueba buscar letra correcta (amarilla)
            for (int k = 0; k < 5; k++){
                if (palabraWord[j] == answerTemp[k] && colorForLetter[j] != 2) { 
                //Si la letra es igual a la letra de la respuesta, pero no ocurrio la condicion anterior, se pintara amarilla (letra correcta, posicion incorrecta)
                    colorForLetter[j] = 1;
                    answerTemp[k] = '-';
                }
            }
        }

        for (int m = 0; m < 5; m++) { 
            if(attempts_end == 6){ //Si el jugador ya llega a los 6 intentos, su ultimo palabra será pintado de rojo y se acaba el programa
                for (int t = 0; t < 5; t++){
                    System.out.print(ANSI_RED + palabraWord[t] + ANSI_RESET);        
                }
            System.out.print("\nAlcanzaste 6 intentos y ahora ya no tienes más oportunidades, vuelve a intentarlo");
            System.exit(0);
            }

            if (colorForLetter[m] == 0) System.out.print(palabraWord[m]); //Color gris (predeterminado)
            if (colorForLetter[m] == 1) System.out.print(ANSI_YELLOW + palabraWord[m] + ANSI_RESET); //Color amarillo, posicion incorrecta
            if (colorForLetter[m] == 2) System.out.print(ANSI_GREEN + palabraWord[m] + ANSI_RESET);  //Color verde, letra correcta
        }
        System.out.println("");
        System.out.println("==================");
        return correct;
    }

    public static String ReturnRandomWord(){ //Arreglo de strings con las palabras que se usaran en el juego
        String[] answerList = {"palas", "cabra", "apoya","ansia","amigo","armar","antes","ahora","abrir","audaz","�nodo","anulo","anuda","anual","antro","atlas","animo","a�ejo","anden","andar","anime","anida","anexa","anglo","�ngel","anexo","�pice","apios","apilo","apolo","apodo","aovar","aorta","apa�o","apaga","apelo","apego","apago","andan","aluza","alzas","alzar","ama�a","aludo","alud�","alzos","amago","amaba","aloes","almos","altos","altas","altar","amasa","ancas","ancla","ancho","anan�","ambos","amena","ambas","anudo","almas","asoma","asear","ascua","ata�d","astro","asumo","atajo","artes","ardan","arcos","arcas","�rbol","ardor","apuro","apure","�rabe","aquel","arena","aroma","armar","arroz","arepa","alfil","�cido","ach�s","acabo","abuso","acata","adi�s","act�o","acuse","abono","�baco","abeto","�lamo","a�sla","ajeno","alg�n","ali�o","alias","alfil","�lbum","aleta","alel�","airea","adule","afina","afila","aforo","a�reo","adulo","aguzo","ahogo","�gape","agota","agita","agraz","agudo","agria","babas","biche","babel","berro","brote","besos","bac�n","bacas","barro","bases","beber","basta","bong�","bache","brisa","badea","bolsa","bolso","broma","badil","bafle","breve","bah�a","becas","baile","belga","bajar","biela","baj�o","blusa","bajos","bizco","blues","brazo","balad","basar","bigas","buf�n","buj�a","batey","bombo","boh�o","balar","breva","baria","bueno","balas","betel","bicho","baldo","bollo","bofar","bal�n","balsa","bal�n","bamba","bamb�","bomba","balto","banal","banyo","banzo","bant�","ba�ar","ba�il","barba","ba�os","barca","beata","bardo","borda","bar�n","barra","barre","barza","batik","beige","bella","bello","bemol","bemba","bid�n","bilis","bet�n","borda","botar","boxeo","brama","brujo","bucal","bud�n","bucle","burdo","cabal","cable","cabos","cacao","cactus","ca�as","calco","caldo","calce","calas","calar","calma","calor","calvo","calza","campo","canal","canas","canes","caney","canje","canoa","canon","cansa","canto","canal","ca�as","ca��n","caobo","ca�os","capaz","caray","caras","cardo","carey","cargo","carie","carpa","caros","carta","carro","casar","casco","caspa","casto","causa","catre","cuaje","causa","cauto","cavar","cazar","cebar","cedro","ceder","cejas","ceibo","celar","celda","cenar","comic","censo","cenit","cerca","cerco","cesto","cetro","chapa","checo","chico","chips","choza","chuzo","cidra","ciclo","cifra","ciego","cinta","cinco","circo","clama","civil","clavo","clero","clima","colmo","combo","coche","comer","costa","costo","coxis","croar","cromo","cruce","crudo","cueva","curva","cutis","dados","dagas","damas","dando","dador","dan�s","danza","da�ar","da�os","dardo","darse","d�til","datos","deber","deca�","debut","decir","dedal","dedos","dejar","delta","denso","dent�","desde","deseo","deuda","diada","dicha","dicho","dicta","diera","dieta","digno","dijes","diodo","diosa","disco","div�n","doble","dobla","d�cil","dogma","doler","dolor","domar","donar","donde","dones","dopar","dorar","dorm�","dotar","dorso","dosis","drago","drama","dreno","ducha","ducto","dudar","duelo","due�a","dulce","duque","durar","duros","�bano","ebrio","echar","ecuas","edema","edito","educa","efuso","egida","egeas","ejote","eleva","elfos","elige","elijo","�lite","ellos","ellas","elles","elote","eluda","elude","emit�","emula","enano","enc�a","enema","enojo","entre","env�a","eolio","�pica","�poca","equis","ergu�","ergos","eriza","erig�","errar","error","esp�a","esqu�","estar","estoy","etano","�tica","�timo","etnia","evado","evito","evoco","exige","�xito","�xodo","facha","f�cil","facto","faena","fagot","fajar","falaz","falco","falda","falla","falso","falta","fango","feria","farra","farol","farsa","fasto","fatal","fauna","favor","fecha","feliz","felpa","f�mur","f�nix","feroz","ferry","feudo","fibra","fiare","ficha","fideo","fiera","fijar","filas","filme","final","finca","fing�","fines","firma","flash","flama","flaco","fleco","flojo","flora","flota","flema","flu�a","flujo","fobia","fog�n","folio","forc�","forja","forma","f�sil","fot�n","frase","friso","fre�r","freno","fresa","fruta","fuego","fuera","funda","furor","fusil","fusta","gab�n","gafas","gaita","gajos","gal�n","galas","gal�s","gal�n","ganar","garra","gases","gasto","gatea","gemas","gemir","genio","gente","gesta","girar","giros","glas�","gleba","grifo","globo","gnomo","gozar","golpe","gordo","gorro","gotas","goteo","gozar","grabe","grada","grado","grafo","grama","gramo","grano","grapa","grasa","grato","grave","greca","grial","grito","grill","grima","gripe","grito","gru�e","grumo","grupo","gruta","guapa","guayo","gueto","guiar","gui�o","gusto","habas","haber","h�bil","habla","hacer","hacha","hacia","halar","halla","hampa","harto","hasta","hazlo","hebra","hecho","heces","hedor","helar","helio","henar","herir","h�roe","herr�","herv�","hiato","hidra","hielo","higos","hilar","himno","hind�","hindi","h�per","hobby","hojas","hogar","hondo","hongo","honro","horno","horas","hotel","hueco","huele","hueso","huevo","huida","humor","hurto","ibero","icono","ideal","ideas","�dolo","�gneo","igual","ilesa","iluso","imana","imita","impar","imp�o","ingle","infla","insto","inter","iones","infra","ir�as","istmo","�talo","izara","izare","izote","jadea","jacta","jab�n","jagua","jaiba","jalea","jal�n","jamar","jaque","jarca","jarra","jarro","jauda","jaula","javas","jay�n","jedar","jefas","jerez","jol�n","jopar","joven","joyas","juicio","jud�o","juego","juega","jugar","junio","jumar","junta","junco","jurar","justo","juzga","kanji","kappa","karma","karts","kayak","k�fir","kilos","kirie","koin�","kurda","kurdo","labio","labro","lacia","labor","lacta","ladea","lados","ladra","laico","lamer","lanas","lance","land�","lanza","lanz�","lapos","lapso","l�piz","largo","larva","lares","latas","l�tex","latir","lat�n","leuda","lavar","laxos","lazar","l�ase","leche","legal","legar","logro","leg�a","lejos","lento","le�ar","le�os","lepra","letal","levar","leyes","leves","liada","liana","l�ber","libra","libre","libro","licua","liceo","licor","l�der","lidio","ligar","light","lijar","lim�n","limas","linda","l�nea","linio","lirio","lista","listo","litro","llama","llaga","llave","llego","llena","lleve","llora","local","logro","logos","lucha","lucid","lucro","luego","lugar","lunar","lunes","lupus","luxar","luzco","madre","mafia","magma","magna","magra","magia","majal","malos","malla","mam�s","mamas","mamey","mam�n","momia","manar","manda","mando","menea","manga","mango","man�a","manos","manta","mansa","ma�as","maor�","mapeo","marca","marea","marzo","marte","matar","matiz","mecer","mecha","media","medir","medio","megas","mel�n","memes","manso","menso","menos","mente","menta","mutar","merco","merey","metal","metas","meter","metro","micro","minar","miope","mioma","mirar","misil","mismo","mixto","modem","mofar","moh�n","molar","molde","momia","mundo","monje","monja","moral","morir","mot�n","mover","mucho","mueca","muela","mujer","mundo","mutuo","nabos","n�car","nacer","negro","nadar","nadir","nadie","nafta","naipe","nardo","nariz","narra","nasal","nat�o","naval","naves","nebl�","necio","negar","nevar","nexos","nicho","niega","nieve","nuevo","nil�n","nieto","nieta","ninfo","ni�ez","nivel","nobel","noche","nitro","nodos","nogal","norma","norte","notar","nubes","n�bil","nuble","nuero","nuevo","nueve","�oras","�ongo","�aque","�ecas","�atos","�atas","�apas","�ango","�anga","�oqui","�ames","�ajos","�onga","�o�ez","�ocas","�o�os","�ique","�ipes","�inga","obeso","oblea","�bito","oboes","obrar","obt�n","obvio","ocaso","octal","ocumo","ocupa","odiar","oeste","odres","ohmio","o�ble","o�dos","ojal�","ojear","ojera","olivo","omega","omino","omiso","omita","ondas","ondea","onoto","opaco","opera","�pera","opino","opone","optar","orcos","orden","orear","oreja","orfos","orina","ori�n","oruga","osada","ostia","oto�o","ovalo","otras","�vulo","oxido","ozono","pacto","padre","pagar","paila","pajar","palco","polea","palca","palma","panal","panel","papal","pap�s","papas","papel","parar","pardo","pared","parir","parla","parto","parva","parte","pasar","pasas","paseo","pasmo","pasos","pasto","patas","pat�n","patio","pausa","pauta","peaje","pecar","pecho","pedal","pedir","pegar","peine","penal","penar","penca","penas","pe��n","peque","perno","pesar","pesca","pez�n","piano","picar","pecas","picor","pilla","pinta","pinza","pi��n","pique","pisar","pista","pitar","pixel","pizca","p�vot","pizza","placa","place","plaga","plano","plata","plato","playa","plaza","plazo","plano","plomo","pluma","podar","poder","poeta","polca","polen","polea","polvo","poner","posar","poste","prado","prima","primo","prior","prisa","prosa","pudin","p�ber","pudor","pugna","pujar","pulir","pulso","pulse","punir","punta","punza","purga","puzle","quark","queco","queda","quedo","queja","quema","quem�","quena","quepo","quera","quien","qui�n","queso","quijo","quila","quipa","quina","quino","qui�o","quiz�","quite","quito","rabia","rabas","roc�o","radar","recio","radio","rad�n","ragua","rahez","ra�do","rioja","rajar","rajen","raigo","ralla","ramas","rampa","rango","rapar","rapaz","rapte","repta","rapto","rasar","rasco","rasca","rasgo","raspa","raspe","ratos","rauda","raudo","rayar","rayos","raz�n","remar","reata","recae","roble","recen","reca�","recta","recia","regia","regla","regar","reh�n","reino","reloj","renal","remos","renta","re�ir","reojo","resta","retar","reten","reuma","retro","re�ne","re�so","rev�s","rezar","riego","rifar","rifle","rigor","rimas","rinde","ruina","rival","ritmo","robar","roble","robot","rodar","rodeo","rogar","ro�do","rollo","rombo","rompe","ronca","rosca","rubor","rubro","rugir","rumba","rumie","saber","sacar","sabio","sabor","sacia","sacro","sagaz","saj�n","salar","salda","sold�","saldo","salir","sali�","sall�","salme","salmo","salsa","sal�n","salta","salte","selva","salve","sam�n","salto","salud","salve","salvo","samba","samia","sanar","sanco","santa","santo","sarda","sarna","saqu�","sat�n","sauce","sardo","sauco","sauna","savia","saz�n","secar","secta","secua","sedal","sedar","segar","seg�n","segur","sello","senda","senil","se�al","se�or","sepia","septo","seres","serio","sexto","sigla","siglo","silla","s�mil","sismo","sitio","sobre","socas","socia","socio","sodio","solar","somos","sonar","so�ar","soplo","sordo","sorbo","suave","sucio","sudar","suelo","suero","sufre","sumar","s�per","sutil","tabla","tacho","tac�n","tacto","tacos","tah�r","taima","taino","taita","tajar","talar","tajos","talco","tales","talla","tal�n","talud","tamiz","tanga","tango","tanda","tanta","tanto","ta�ar","tapar","taran","tarde","tardo","tarea","tarot","tasar","tasca","tarro","tasco","tat�o","tax�n","tazar","tauro","techo","tecla","tedio","tejas","tejer","telar","tel�n","temas","temer","temor","tempo","tener","tend�","tenis","tenor","tenso","terco","temor","termo","terso","tesis","tetra","texto","tibio","tiara","tirso","tieso","tilde","til�n","timar","tim�n","tinto","te�ir","tiple","tirar","tir�n","tit�n","tocar","toldo","tomar","tomos","tonto","t�nel","topar","toque","t�rax","tosco","toser","t�tem","traer","trama","trans","trapo","trato","trial","tribu","trina","tripa","triza","trozo","tropa","troto","trova","trono","truco","tumba","tupir","turbo","turno","tutor","ubres","ubico","ubica","ufana","ulema","ultra","ulula","umbra","uncen","unido","uncir","unc�a","�nete","ungir","uni�n","untar","unzan","untos","Urano","urd�a","urbes","urdir","urgi�","urjas","�rico","�rica","usado","usara","usar�","usase","us�as","usted","usual","usure","�tero","�vula","vac�o","vadea","vagar","vag�n","video","valer","valla","valor","vamos","vapor","vanos","varar","vario","var�a","var�n","vasco","v�ter","vatio","vayan","v�ase","vedar","vejez","velar","vel�n","vence","vende","vengo","venia","venta","venus","veras","venia","verbo","verso","verm�","vert�","viaje","vibra","vicio","viejo","vieja","vig�a","vigor","villa","viral","visco","vital","vivaz","vivir","viudo","visto","viste","vocal","vodka","volar","votar","vuelo","watts","weber","xecas","xen�n","xinca","xiote","xolas","xolos","yacer","yates","yelmo","yergo","yendo","yenes","yerba","yedra","yerma","yerna","yerno","yerra","yesca","yogui","yogur","yumbo","yodos","yunta","yezgo","zafar","zafio","zafia","zagas","zagal","zamba","zanco","zanja","zarpa","zarzo","zenda","zetas","zocas","zombi","zueco","zumba","zumbo","zupia","zu�ir","zurc�","zurdo","zurda","zurza","Jacob","Mar�a","Susan","�scar","Laura","David","Nadia","James","Jes�s","Josu�","Javier","Sara�","Saray","Aim�","Alexa","Anais","Lucas","Adamo","�gata","Agnus","Zoila","�ngel","Antia","Berta","Vilma","Bimba","Juana","Celia","C�sar","Dalia","Nubia","Lucia","Sof�a","Lucas","Dar�a","Dar�o","Mario","Mauro","Diana","Frank","Dimas","Edipo","Nicol","Jolie","Julio","Ester","Guido","Paula","Tania","Linda","Lucio","Marco","Pablo","Ram�n","Sasha","Romeo","C�diz","Ibiza","Argel","Cuzco","Pap�a","Cauca","Andes","Alpes","Sudan","Congo","China","Congo","Eji�n","Judea","Kenia","Libia","Mosc�","Petra","Nepal","Quito","Saud�","Vegas","Siena","Macao","Suiza","�and�","ara�a","abeja","oveja","ameba","cebra","hiena","yegua","perro","perra","llama","cerdo","bagre","l�mur","cobra","coqu�","okapi","leona","tigre","lince","burro","burra","gallo","sa�no","zorro","zorra","corzo","erizo","ganso","pit�n","simio","ostra","tuc�n","ovino","mamut","garza","koala","mirlo","morsa","panda","larva","piojo","potro","potra","cor�a","mosca","mosco","cabra","cebra","danta","coat�","pulpo","tenia","chivo"};

        List<String> words = Arrays.asList(answerList).stream().filter(v -> !v.contains("�")).collect(Collectors.toList());//Eliminamos las palabras con tildes �

        String Respuesta = words.get((int)(Math.random() * (words.size() - 1))); //Asigna una palabra aleatoria de la lista "words"
        //System.out.println("LA RESPUESTA ES: " + Respuesta); //Test para saber la respuesta
        return Respuesta;
    }
}