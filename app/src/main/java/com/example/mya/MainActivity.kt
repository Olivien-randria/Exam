package com.example.mya

import androidx.navigation.NavHostController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mya.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

            }
    }
}

@Composable
fun MainScreen(){

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope=scope,scaffoldState = scaffoldState)},
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }

    ){
        Navigation(navController = navController)
    }
}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState){

    TopAppBar(
        title = { Text(text = "Fandraharahana", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "" )
            }
        },
        backgroundColor = Color.Green,
        contentColor = Color.Black
    )

}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){

    val items = listOf(
        NavigationItem.Introduction,
        NavigationItem.Selfgrowth,
        NavigationItem.Communication,
        NavigationItem.Leadership,
        NavigationItem.Management,
        NavigationItem.Accounting,
        NavigationItem.Marketing,
        NavigationItem.Negociation,
        NavigationItem.Enterprenership
    )

    Column (
        modifier = Modifier
            .background(color = Color.White)
            ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = " ",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items, selected = currentRoute == items.route, onItemClick = {

                navController.navigate(items.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Dev Olivien Rd",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DrawerItem(item: NavigationItem, selected:Boolean,onItemClick:(NavigationItem)->Unit){
    val background = if(selected)R.color.grey else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)

    ){
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
/* Fampidirana    */
@Composable
fun IntroductionScreen(){
    Column() {
        HeaderIntroUI()
        DescriptionIntroUI()
        FooterIntroUI()
    }
}
@Composable
fun HeaderIntroUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
            ) {
        Column {
            Text(
                text = "LASA IZAO",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun   DescriptionIntroUI() {
    Card(
        backgroundColor = TealCard,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        elevation = 0.dp
    ) {
        Row {
            Column {
                Text(
                    text = "Ny fanavaozana ny toetsaina, ny fotom-pisainana izay mitandro ny fanahy maha olona no hampitsiry " +
                            "indray ny endrim-piarahamonina vaovao mandala ny MARINA -RARINY-HITSINY izay  fitsimpiarahamon’ny " +
                            "Ntaolo malagasy. « Rehefa fatrao ny harembe nomen'Andriamanitra anao nanomboka tao ambohoka (aina, " +
                            "vatana, saina, fanahy, fotoana) dia afaka manao business na mandraharaha ianao ,anjaranao no mamatatra " +
                            "ireo arena be ireo. »",
                    fontSize = 14.sp,
                    color = SubTextColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lead),
                contentDescription = " ",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}
@Composable
fun FooterIntroUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "ANDAO ARY!",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Fotoana izao hanovanao ny fiainanao",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
/*   OLONA SAHY   */
@Composable
fun SelfgrowthScreen(){
    Column() {
        HeaderSelfUI()
        DescriptionSelfUI()
        FooterIntroUI()
    }
}
@Composable
fun HeaderSelfUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "OLONA SAHY",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionSelfUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Voalohany indrindra, ireto fanotaniana ireto apetraho amin'ny tenanao:" +"\n"+
                    "•\tInona no antom-pisiako ? Inona no tanjoko eto amin’ity fiainana ity ? (Fataro ny antom-pisianao)\n" +
                    "•\tIzaho ve ilay olona resy eo amin’ny fiainana sa ilay olona mandresy ?\n" +
                    "•\tNahoana izaho no tsy mahomby ? Fa Nahoana izy no mahomby(manakarena) ? (Ianaro ny fiainany sy ireo antony mampanakarena azy ka alaivo tahaka izay mety ary hatsarao sy ampio mba ho lasa lavitra no izy enao, aza alaina izay ratsy)\n" +
                    "•\tSamy nomena 24 ora ny olona rehetra, fa nahoana izy mavita zavatra goavana amin’izany ?\n" +
                    "\n" +
                    "Mino aho fa mampiheritreritra anao izany, indrindra indrindra hampitsiry ny hambom-po hatonga fihezahana hanao zavatra tsara. \n"
                    +"\n"+
                    "Manaraka izany,ireto zavatra zavatra ireto dia tsara ialana :\n" +
                    "•\tTaotra(Andriamanitra rery no atahorana)\n" +
                    "•\tTsy manao zavatra matotra : Ataovy tsara ny fianarana na tsisy mpijery ary.\n" +
                    "•\t Tsy matoky tena (rehefa matoky ianao dia tedavin'izao tontolo izao, dia fataro ny lalanao amin’ny sehatr'asa? (Iasa amin’ny olona ve sa ampiasa olona?)\n" +
                    "\n" +
                    "Ireto kosa dia mila hananao:\n" +
                    "•\tManana vina, tanjona\n" +
                    "•\tSahy\n" +
                    "•\tManao Défie (Vitako io )\n" +
                    "•\tManana paik’ady araka izay zavatra ananana.\n" +
                    "•\tMiasa\n" +
                    "•\tManatsara sy mampifanaraka izany amin’ny zava misy.\n" +
                    "\n"+
                    "Tsarovy fa na miasa amin'olona ianao na mandraharaha dia isany mahatonga ny firoboroboan'ny asanao ireo:\n" +
                    "•\tTso-po (Sincère)\n" +
                    "•\tMilaza ny marina (Honnête)\n" +
                    "•\tMatoky (Confident)\n",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }
}
/* Fahaiza-mifandray */
@Composable
fun CommunicationScreen(){
    Column {
        HeaderComUI()
    }
}
@Composable
fun HeaderComUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MIFANDRAY",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}

/* Fahaiza-mitarika */
@Composable
fun LeadershipScreen(){
    Column() {
        HeaderLeadUI()
        DescriptionLeadUI()
        FooterIntroUI()
    }
}
@Composable
fun HeaderLeadUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MITARIKA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionLeadUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ny teny hoe \"LEADERSHIP\" dia azo avy amin'ny fototeny anglisy hoe \"Lead\" izay midika hoe \"Guider\" na \"Conduire\" amin'ny teny frantsay ary midika hoe \"MITARIKA\" amin'ny teny Malagasy. \n" +
                    "\n" +
                    "Averiko amafisina ireto teny ireto :\n" +
                    "•\tLeadership : haitarika, fitarihana\n" +
                    "•\tLeader : Mpiatarika\n" +
                    "•\tBonne leadership : fitarihana tsara\n" +
                    "•\tBon leader : Mpitarika mahay\n" +
                    "•\tInona no tarihana ? : Olona no tarihana mba hatrarana ny tanjona sy vina\n"
                    +"\n"+
                    "Araka izany, ny fitarihana dia fahefana, fahaizan’olona iray miatraika amin’ny fitondrana ny hafa.\n"
                    +"\n"+
                    "Ny mpitarika dia olona mihavaka, ka ireto manaraka ireto dia tokony ananan’ny mpitarika :\n" +
                    "•\tManana vina, fanamby, faniriana (maharitra) : vision (visionnaire), challenge (challenger), ambitieux\n" +
                    "•\tManana paik’ady : stratégie\n" +
                    "•\tTso-po, milaza ny marina, matoky : sincère-Honnête-Confident\n" +
                    "•\tEke-teny : Charisme\n" +
                    "•\tManana fihetsehampo : émotion\n" +
                    "\n"+
                    "Raha fitinina ny anjara asan’ny mpitarika dia toy izao:\n" +
                    "•\tManao fanamby : Lancer des défis\n" +
                    "•\tManome tohana sy mankahery ny tarihina : influer (influencer), motiver\n" +
                    "•\tManoro lalana : guider, orienter\n" +
                    "•\tMifandray tsara amin’ny tarihina ary mitatitra ny zava misy : communiquer\n" +
                    "•\tManome image tsara amin’ny ho avy : donner une image attrayante à l’avenir\n" +
                    "\n"+
                    "Marihina fa ny fitarihana dia mifototra amin’ny communication.\n" +
                    "\n"+
                    "Anisany mampiavaka ny Mpitarika :\n" +
                    "•\tTanjaka sy fikirizana : Energie et persévérance\n" +
                    "•\tOlon’ny andraikitra-Manana capacité amin’ny fiheverana ny andraikitra : Capacité à assumer les responsabilités\n" +
                    "•\tMiasa : aptitude à l’action\n" +
                    "•\tManampaniriana : Ambitieux\n" +
                    "•\tManana tsirin-kevitra : sens d’initiative\n" +
                    "•\tMijery ny lafitsara : optimisme\n" +
                    "•\tMieritreritra anao zavatra tsara : sens de qualité\n" +
                    "•\tMahay miara-ory : empathie\n" +
                    "•\tMahay mandresy lahatra : Talent pour persuader\n" +
                    "•\tEke-teny :charisme\n" +
                    "•\tManana style-strategie communication : Aptitude à la communication\n" +
                    "•\tHaingana amin’ny fitsarana sy fandraisana fanampan-kevitra tsy mitanila : Rapidité de jugement et de décision\n" +
                    "•\tMatoky tena : Confiance en soi\n" +
                    "\n"+
                    "Eo amin’ny sehatry ny fitarihana dia misy izay hoe « style leadership izay » ka misy karazany maro izany fa eto dia ireto telo ireto no aseho anareo :\n" +
                    ".\tAutoritaire-Autocratic : Ny mpitarika no mandray fanampakevitra manokana ary mampiatra izany.\n" +
                    ".\tDémocratique-Democratic : Ny fanampakevitra dia vokatrin’ny fifampiresahana na adihevitra eo amin’ny mpitarika sy tarihana.\n" +
                    ".\tLaissez-faire-Let them do : Manome tanjona mazava sy fitaovana ny mpitarika ary avelana hanatateraka izay.\n",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }

}
/* Fahaiza-mitatana */
@Composable
fun ManagementScreen(){
    Column() {
        HeaderManageUI()
        DescriptionManageUI()
        FooterIntroUI()
    }
}
@Composable
fun HeaderManageUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MITATANA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionManageUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ny teny hoe \"MANAGEMENT\" dia azo avy amin'ny fototeny anglisy hoe \"Manage\", izay midika hoe \"Gérer\" amin'ny teny frantsay ary midika hoe \"MANDAMINA\" amin'ny teny Malagasy. Ilaina izy io rehefa hitantana orinasa.\n" +
                    "\n" +
                    "\n"+
                    "Inona no alamina? => Fotoana, birao, orinasa amin'ny ankapobeny. Eo amin'ny lafin'ny fandaminana orinasa indrindra no mipetraka ilay fanontaniana hoe: \"Iza no manao inona ?\". Miainga amin'izay fanontaniana izay ny fandehan-javatra rehetra ary ny anjara asan'ny \"Manager\" amin'izany dia ny \"mandamina\" ny fandehan'ny orinasany. Azo lazaina fa mahafehy ny atao hoe Management ny olona iray rehefa hainy ny mametraka ny tsirairay amin'ny anjara-toerana tokony hisy azy ; rehefa hainy ny manome ny andraikitra sahaza ho an'ny olona rehetra ary mizara izany araky ny tokony ho izy ; rehefa hainy ny manara-maso ny mpiasany isan-tsokajiny ary indrindra indrindra rehefa hainy ny mifehy azy ireo.\n" +
                    "\n" +
                    "\n"+
                    "Ny atao hoe \"Management d'entreprise\" araka izany dia fahaizana MANDAMINA ny fandehan-javatra sy ny toe-draharaha rehetra eo anivon'ny orinasa iray.\n",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }
}
/* Fahaiza-mitatana vola */
@Composable
fun AccountingtScreen(){
    Column() {
        HeadAccounUI()
        DescriptionAccounUI()
    }
}
@Composable
fun HeadAccounUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MITATANA VOLA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionAccounUI(){

}

/* Fahaiza-mivarotra */
@Composable
fun MarketingScreen(){
    Column() {
        HeadMarUI()
        DescriptionMarUI()
    }
}
@Composable
fun HeadMarUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MIVAROTRA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionMarUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Misy miteny hoe aza mandraharaha raha tsy mahay mivarotra. Ye, marina izany.\n" +
                    "Tadidio fa rehefa handraharaha ianao dia tsy maintsy hivarotra(hivarotra service , na hivarotra produit). \n" +
                    "Tsy ny mpivarotra rehetra anefa ny mahalafo fa ireo mampiasa technique ihany. Tsy ny mpandraharaha rehetra no mahomby fa ireo mampiasa methode ihany. \n" +
                    "Araka izany, mampiasà technique  fa ny mpivarotra tena mahay dia mampiasa technique sy methode avokoa. \n" +
                    "\n"+
                    "Ireto misy technique afaka ampiasainao :\n" +
                    "\n"+
                    "1- Manaova promotion (Réduction de prix) \n" +
                    "Tsy maha perte anao tompoko ny manao réduction de prix amin'ny produits na amin'ny service nao mba ho azon'ny rehetra jifaina rehefa misy fety ( Noël, Ramadan, Pâques, fête des pères...). \n" +
                    "Tadidio fa ny olona manandrana ny produits na service nao dia afaka mampiditra client vaovao ao aminao.\n" +
                    "Satria maninona?\n" +
                    "Satria:\n" +
                    "-Lasa solotenanao mandehandeha, (ambassadeur)\n" +
                    "-Lasa dokambarotra-nao mandehandeha, (Publicité)\n" +
                    "-Lasa CV-nao mandehandeha (Profil). \n" +
                    "Mino aho fa efa nahita orinasa be dia be nanao promotion na Réduction de prix ianao.\n" +
                    "Izany no antsoina hoe Neuro-Marketing \n" +
                    "2- Manaova dokam-barotra amin'ny réseaux sociaux. (Facebook) \n" +
                    "Ny publicité Facebook, ny fanokafanao pejy ho an'ny orinasanao dia hanampy betsaka anao sy ny orinasanao hanana crédibilité sy visibilité. \n" +
                    "Tsy misalasala intsony ny olona fa manana assurance hoe mi-existe marina ny orinasanao fa tsy arnaque.(soloky) \n" +
                    "Tadidio fa anisany hampiakatra ny confiance n'olona aminao sy amin'ny orinasanao ny présence sur internet. \n" +
                    "Technique iray ihany koa ahafahanao mampiditra client vaovao ao anaty orinasanao. \n",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }

}

/* Fahaiza-mifampiraharaha */
@Composable
fun NegociationScreen(){
    Column() {
        HeadNegUI()
        DescriptionNegUI()
    }
}
@Composable
fun HeadNegUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MIFAMPIRAHARAHA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionNegUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Amin’ny ankampobeany, ny zavatra rehetra dia azo ifampiraharana . Tsarovy fa ny fampiraharahana dia tsy ady fa natao amoroanana « valeur ».\n" +
                    "Ahoana no atao raha hifampiraharaha amin’olona ?\n" +
                    "\n"+
                    "Misy karazany roa raha ifampiraharaha :\n" +
                    "\n"+
                    ".\tFifampiraharahana mifatoka amin’ny fizarana « négociation distributive ». Ao anatin’izany dia izay mahazo amin’ny ampahany iray dia tsy mahazo amin’ny ampahany iray. Araka izany io karazana fifampiraharahana io dia manodinana ny zavatra iray toy ny PRIX.\n" +
                    "\n"+
                    ".\tFifampiraharahana samy mahazo tombony « Integrative » : ao anatin’izany dia ampiasaina ilay zavatra ifampiraharahana mba ho « opportunité » mba hahazoana tombony iraisana. Samy mahazo tombony no tanjona ao anatin’izany.\n" +
                   "\n"+
                    "Tsara ny manamarika fa mialohan’ny hifampiraharaha dia « tsara ny tsara fiomanana ». Fataro tsara ilay olona na orin’asa ifampiraharahanao. Omano tsara ny saina, vatana ary fanahy.\n ",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }


}
/* Fahaiza-mandraharaha */
@Composable
fun EnterprenershipScreen(){
    Column() {
        HeadEnterUI()
        DescriptionEnterUI()
    }
}
@Composable
fun HeadEnterUI(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "FAHAIZA-MANDRAHARAHA",
                fontSize = 18.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sahia miteny hoe hanova aho",
                fontSize = 14.sp,
                color = SubTextColor,
                fontWeight = FontWeight.Medium
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logos) ,
            contentDescription =" ",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun DescriptionEnterUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ny fahandraharahana dia fitambaran’ny fitatanana sy fitarihana." +
                    "Zavatra anankiroa tsy afa-misaraka ny \"Management\" sy ny \"Leadership\" eo amin'ny sehatry ny fitantanana orinasa. Raha voafehinao tsara izy roa ireo, dia azonao atao soaman-tsara ny miasa eo amin'ny tontolon'ny \"Entrepreneuriat\".\n" +
                    "\n"+
                    "Torohevitra:\n" +
                    "•\tAfaka manomboka amin'ny kely eo ampelatanana ianao fa ny manomboka amin'ny tsy misy \"impossible\". \n" +
                    "•\tRehefa hanangana orinasa dia tsy vola no ilaina voalohany, tsy vola no ilaina faharoa, tsy vola no ilaina fahatelo fa misy dingana mila arahina raha te hahomby amin'ny domaine iray ianao. \n" +
                    "\n"+
                    "AVY AIZA NY PROJET ?\n" +
                    "\n"+
                    "Mianga amin'ny étude an’ilay toerana (firenena, renivohitra, distrika, kaominina, fokotany) :\n" +
                    "Tanjona amin’izay no ahalalana ny zava-misy ao amin’ilay toerana :\n" +
                    "•\tOtrin’ny aona ny environnement (Etude environnemental)\n" +
                    "•\tOtrin’ny aona fiarahamonina, ny mponina, manodidina firy ny isan’ny mponina (Etude du societé, population)\n" +
                    "•\tOtrin’ny aoana ny fandriampahalemana (Etude de securité)\n" +
                    "•\tOtrin’ny ahoana ny économie ao, inona no asan’ny olona ao, ny farimpianan’ny olona,inona no efa zava misy ao, ny vokatra miakatra ao (Etude économique)\n" +
                    "•\tOtrin’ny aona ny commerce ao, inona avy ny zavatra amidy ao, inona ny filana fototra ao (Etude commerciale)\n" +
                    "•\tOtrin’ny aona ny logistique, aoana ny fanafarana sy famoahambokatra ao\n" +
                    "•\tOtrin’ny aona ny hoavin’ilay toerana (Etude l’évolution de l’endroit)\n" +
                    "Izany rehetra izany no atao mba hafatarana ny filana fototra ao amin’ilay toerana araka ireo étude rehetra ireo.\n" +
                    "Listeo daholo ireo problem misy eo amin’ilay toerana (ny olana rehetra midika ho famoronana asa)\n" +
                    "Inona avy ireo vahaolana mifandraika ireo olona (varieté des solutions en fonction des ressources).\n" +
                    "Mitady ilay meilleur solution en fonction des ressources (plus rentable) isakin’ny olana tsirairay.\n" +
                    "Ilay meilleur solution iny no Objectif na titre de projet na idée de business\n" +
                    "\n" +
                    "\n" +
                    "ETUDE DU PROJET\n" +
                    "\n"+
                    "1-\tFarito mazava tsara ilay projet.\n" +
                    "Fananganana ny cadre logique : Inona avy ny amabaratonga arahina atratrana io tanjona io ? Inona avy ireo  tanjona tratrarina mba atongavana io tanjona iray io.\n" +
                    "Atsangano ny organigramme, diagramme de flux an’ilay projet.\n" +
                    "\n"+
                    "2-\tInona avy ireo stratégie na méthode\n" +
                    "Tsy ny olona rehetra no nantsoina ho mpandraharaha :\n" +
                    "•\tMila manana toe-tsaina mpandraharaha ianao rehefa handraharaha.\n" +
                    "•\tMila miverina mianatra (sacrifice), \n" +
                    "•\tMila mahafehy tena (discipline)\n" +
                    "•\tMila mahay mitarika (Leadership)\n" +
                    "•\tMila mahay mivarotra (marketing)\n" +
                    "•\tMila mahatoky tena (Developement personel) .\n" +
                    "Ato amin'ny teboka faharoa no hamolavolanao ny momba anao. \n" +
                    "\n" +
                    "\n"+
                    "3-\tEtude de marché, technique de vente (marketing)\n" +
                    "Inona no service na produit alefako eo amin’ny marché\n" +
                    "Mahavaha ny olan’ny mpanjifa ny sevice omeko ?\n" +
                    "Mamaly ny filan’ny mpanjifa ve ?\n" +
                    "Iza avy ireo cible ?\n" +
                    "Inona avy ireo technique de vente ampiasaiko ? (Paik’ady alafosana) : affichage, publicité, animation vente, neuro-marketing ,vente de promotion (réduction prix), ,SMS marketing..\n" +
                    "\n"+
                    "4-\tPrevision financiere\n" +
                    "Manangana ny business plan, Otrinona ny chiffre d’affaire ? Mety otrinona ny vola miditra sy vola mivoaka ?. \n" +
                    "\n" +
                    "\n"+
                    "5-\tRecherche de finacement\n" +
                    "Ato amin'ny teboka fahadimy vao miresaka \"fond de démarrage\". Comment trouver un financement?\n" +
                    "Izay vao resaka vola amin'izay rehefa nandalo ireo dingana ambony ireo ianao.\n" +
                    "Ato ianao no misafidy na ianao irery ihany no hanangana azy dia miditra \"auto financement\" na hiara hanangana amin'olona, hiara hiasa amin'olona, hi-associer amin'olona dia miditra amin'ny fizarana \"pourcentage\". \n",
            fontSize = 14.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController, startDestination = NavigationItem.Introduction.route ){
        composable(NavigationItem.Introduction.route){
            IntroductionScreen()
        }
        composable(NavigationItem.Selfgrowth.route){
            SelfgrowthScreen()
        }
        composable(NavigationItem.Communication.route){
            CommunicationScreen()
        }
        composable(NavigationItem.Leadership.route){
            LeadershipScreen()
        }
        composable(NavigationItem.Management.route){
            ManagementScreen()
        }
        composable(NavigationItem.Accounting.route){
            AccountingtScreen()
        }
        composable(NavigationItem.Marketing.route){
            MarketingScreen()
        }
        composable(NavigationItem.Negociation.route){
            NegociationScreen()
        }
        composable(NavigationItem.Enterprenership.route){
            EnterprenershipScreen()
        }
    }

}