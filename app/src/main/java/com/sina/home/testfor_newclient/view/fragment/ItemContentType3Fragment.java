package com.sina.home.testfor_newclient.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseFragment;
import com.sina.home.testfor_newclient.bean.ClientBean.NewBean;
import com.sina.home.testfor_newclient.bean.ClientBean.PicBean;
import com.sina.home.testfor_newclient.listener.FinishDataRefresh;
import com.sina.home.testfor_newclient.utils.GlobalConstant;
import com.sina.home.testfor_newclient.view.adapter.NewAdapter;
import com.sina.home.testfor_newclient.widget.ProjectToast.ShortToast;
import com.sina.home.testfor_newclient.widget.pullRefresh.Base.PullToRefreshBase;
import com.sina.home.testfor_newclient.widget.pullRefresh.View.PullRefreshContent.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Walter on 2016/1/19.
 */
public class ItemContentType3Fragment extends BaseFragment implements
        PullToRefreshListView.OnRefreshListener<ListView>,
        AbsListView.OnItemClickListener,
        FinishDataRefresh
{
    private static final String TAG = "ItemContentType3Fragment";
    private final int MSG_UPDATE = 0x01001;
    //widget
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    //data
    private LinkedList<String> mList;//List<String>  //这里使用的是LinkedList 而不是List,是因为Array和List进行转换之后得到的是固定长度的List，不便于扩展。所以这里使用的是linkedList
    private List<NewBean> mListNewData;
    private int mCurIndex = 0;
    private boolean mIsStart = true;
    private static final int mLoadDataPerTime = 100;
    private ArrayAdapter<String> mAdapter;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    private ShortToast shortToast;
    private boolean isAutoRefresh = false;
    private int mSubjectId = 0;
    private GetDataAsyncTask  mGetDataAsyncTask;
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_UPDATE:  //针对ListView的Adapter的更新，应该在UI线程上操作，子线程上操作之后，主线程上不能获得adapter.notifyDataSetChange()的通知。
                    mAdapter.notifyDataSetChanged();
                    mPullToRefreshListView.onPullDownRefreshComplete();
                    mPullToRefreshListView.onPullUpRefreshComplete();
                    setLastUpdatedLabel();
                    break;
            }
        }
    };
    //interface
    /** 下来刷新的接口*/
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
       mIsStart = true;
        mGetDataAsyncTask = new GetDataAsyncTask(this);
        mGetDataAsyncTask.execute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        mIsStart = false;
        mGetDataAsyncTask = new GetDataAsyncTask(this);
        mGetDataAsyncTask.execute();
    }

    /** listView的item的点击接口*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String info = mListNewData.get(position).getTitle() + " ,  index = " + position;
        if(shortToast != null){
            shortToast.cancelShow();
            shortToast = null;
        }
        shortToast = ShortToast.makeText(getActivity(), info, 2000);
        shortToast.setGravity(Gravity.BOTTOM,0,0);
        shortToast.show();
    }

    @Override
    public void doRefreshData() {
        mHandle.sendEmptyMessage(MSG_UPDATE);
    }

    public static  ItemContentType3Fragment getInstance(Bundle bundle){
        ItemContentType3Fragment fragment = new ItemContentType3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return -1;
    }

    @Override
    protected View getLayoutView() {
        mPullToRefreshListView = new PullToRefreshListView(getActivity());
        return mPullToRefreshListView;
    }

    @Override
    protected void findView(View view) {
        mListView = mPullToRefreshListView.getRefreshableView();
    }

    @Override
    protected void initView() {
        mPullToRefreshListView.setPullLoadEnabled(false);
        mPullToRefreshListView.setScrollLoadEnabled(true);
        getData();
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mList);
        //mListView.setAdapter(mAdapter);
        //mListView.setOnItemClickListener(this);

        getNewsData();
        NewAdapter newAdapter = new NewAdapter(getActivity(),mListNewData);
        mListView.setAdapter(newAdapter);
        mListView.setOnItemClickListener(this);
        mPullToRefreshListView.setOnRefreshListener(this);
        setLastUpdatedLabel();
//        if(isAutoRefresh)
//        {
//            mPullToRefreshListView.doPullRefreshing(true, 500);
//            isAutoRefresh = false;
//        }
    }

    private void getData(){
        isAutoRefresh = getArguments().getBoolean(GlobalConstant.ISAUTOREFRESH,false);
        mSubjectId = getArguments().getInt(GlobalConstant.SUBJECT_ID,0);
        mCurIndex = mLoadDataPerTime;
        mList = new LinkedList<String>();
        mList.addAll(Arrays.asList(mStrings).subList(0, mCurIndex));
        mList.add(0,"" + mSubjectId);
    }

    private void setLastUpdatedLabel(){
        String text = formatDateTime(System.currentTimeMillis());
        mPullToRefreshListView.setLastUpdatedLabel(text);
    }

    private String formatDateTime(long time) {
        if (0 == time)
            return "";
        return mDateFormat.format(new Date(time));
    }

   class GetDataAsyncTask extends AsyncTask<Void,Void,String[]>{
       private FinishDataRefresh mFinishDataRefresh;
       boolean hasMoreData = true;
       public GetDataAsyncTask(FinishDataRefresh finishDataRefresh){
           mFinishDataRefresh = finishDataRefresh;
       }

       @Override
       protected String[] doInBackground(Void... params) {
          try {
              Thread.sleep(3000);
          }catch (Exception e){}
           mFinishDataRefresh.doRefreshData();
           return mStrings;
       }

       @Override
       protected void onPostExecute(String[] strings) {
               if(mIsStart){
                   //mList.addFirst("Added after refresh...");  //如果是这里加数据了，每次刷新都会加入数据的
               }else{
                   int start = mCurIndex;
                   int end = start + mLoadDataPerTime;
                   if(end > mStrings.length){
                       end  = mStrings.length;
                       hasMoreData = false;
                   }
                   for(int i=start;i<end;i++){
                       mList.add(mStrings[i]);
                   }
                   mCurIndex = end;
                   mPullToRefreshListView.setHasMoreData(hasMoreData);
               }
           super.onPostExecute(strings);
       }
   }

   private void getNewsData(){
       NewBean bean = null;
       mListNewData = new ArrayList<>();
       Random  mRandom = new Random(3);
       for(int i=0;i<50;i++){
           bean  = new NewBean();
           int radom = (mRandom.nextInt(3) + i) % 3;
           if( radom == 0)
           bean.setTitle("短短6分钟,感动千万归家人");
           else if(radom == 1)
               bean.setTitle("第18份中央一号文件背后:习近平频频支招三农");
           else if(radom == 2)
               bean.setTitle("美司令:中国南海填岛 战时只有美军可以抵抗");

           bean.setType(mRandom.nextInt(3) % 3);

           int radom1 = (mRandom.nextInt(3) + i) % 3;
           if(radom1 == 0)
               bean.setComments("2.4万");
           else if(radom1 == 1)
               bean.setComments("1.1万");
           else if(radom1 == 2)
               bean.setComments("3.8万");

           int radom2 = (mRandom.nextInt(3) + i) % 3;
           if(radom2 == 0)
               bean.setOrigin("新华网");
           else if(radom2 == 1)
               bean.setOrigin("中国青年网");
           else if(radom2 == 2)
               bean.setOrigin("鼎盛军事");

           int radom3 = (mRandom.nextInt(3) + i) % 3;
           if(radom3 == 0)
               bean.setComments("2.4万");
           else if(radom3 == 1)
               bean.setComments("1.1万");
           else if(radom3 == 2)
               bean.setComments("3.8万");

           int radom4 = (mRandom.nextInt(3) + i) % 3;
           if(radom4 == 0)
               bean.getmListPic().add(new PicBean(0, R.drawable.icon_cat2));
           else if(radom4 == 1)
               bean.getmListPic().add(new PicBean(0, R.drawable.icon_cat3));
           else if(radom4 == 2)
               bean.getmListPic().add(new PicBean(0, R.drawable.icon_cat1));

           bean.getmListPic().add(new PicBean(1, R.drawable.icon_cat2));
           bean.getmListPic().add(new PicBean(2, R.drawable.icon_cat3));
           bean.getmListPic().add(new PicBean(3, R.drawable.icon_cat4));
           mListNewData.add(bean);
       }
   }

    public static final  String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
            "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
            "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
            "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
            "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
            "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
            "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
            "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
            "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
            "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
            "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
            "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
            "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
            "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
            "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
            "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
            "Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
            "Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
            "Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
            "Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
            "Cotherstone", "Cotija", "Cottage Cheese", "Cottage Cheese (Australian)",
            "Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
            "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
            "Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
            "Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
            "Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo", "Danish Fontina",
            "Daralagjazsky", "Dauphin", "Delice des Fiouves", "Denhany Dorset Drum", "Derby",
            "Dessertnyj Belyj", "Devon Blue", "Devon Garland", "Dolcelatte", "Doolin",
            "Doppelrhamstufel", "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
            "Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra", "Dunlop", "Dunsyre Blue",
            "Duroblando", "Durrus", "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
            "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne", "Esbareich",
            "Esrom", "Etorki", "Evansdale Farmhouse Brie", "Evora De L'Alentejo", "Exmoor Blue",
            "Explorateur", "Feta", "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
            "Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis", "Flor de Guia",
            "Flower Marie", "Folded", "Folded cheese with mint", "Fondant de Brebis",
            "Fontainebleau", "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
            "Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire", "Fourme de Montbrison",
            "Fresh Jack", "Fresh Mozzarella", "Fresh Ricotta", "Fresh Truffles", "Fribourgeois",
            "Friesekaas", "Friesian", "Friesla", "Frinault", "Fromage a Raclette", "Fromage Corse",
            "Fromage de Montagne de Savoie", "Fromage Frais", "Fruit Cream Cheese",
            "Frying Cheese", "Fynbo", "Gabriel", "Galette du Paludier", "Galette Lyonnaise",
            "Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail", "Garrotxa", "Gastanberra",
            "Geitost", "Gippsland Blue", "Gjetost", "Gloucester", "Golden Cross", "Gorgonzola",
            "Gornyaltajski", "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
            "Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
            "Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh", "Greve",
            "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny", "Halloumi",
            "Halloumy (Australian)", "Haloumi-Style Cheese", "Harbourne Blue", "Havarti",
            "Heidi Gruyere", "Hereford Hop", "Herrgardsost", "Herriot Farmhouse", "Herve",
            "Hipi Iti", "Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
            "Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu", "Isle of Mull", "Jarlsberg",
            "Jermi Tortes", "Jibneh Arabieh", "Jindi Brie", "Jubilee Blue", "Juustoleipa",
            "Kadchgall", "Kaseri", "Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
            "Kikorangi", "King Island Cape Wickham Brie", "King River Gold", "Klosterkaese",
            "Knockalara", "Kugelkase", "L'Aveyronnais", "L'Ecir de l'Aubrac", "La Taupiniere",
            "La Vache Qui Rit", "Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
            "Langres", "Lappi", "Laruns", "Lavistown", "Le Brin", "Le Fium Orbo", "Le Lacandou",
            "Le Roule", "Leafield", "Lebbene", "Leerdammer", "Leicester", "Leyden", "Limburger",
            "Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer", "Little Rydings",
            "Livarot", "Llanboidy", "Llanglofan Farmhouse", "Loch Arthur Farmhouse",
            "Loddiswell Avondale", "Longhorn", "Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam",
            "Macconais", "Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
            "Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses", "Maredsous", "Margotin",
            "Maribo", "Maroilles", "Mascares", "Mascarpone", "Mascarpone (Australian)",
            "Mascarpone Torta", "Matocq", "Maytag Blue", "Meira", "Menallack Farmhouse",
            "Menonita", "Meredith Blue", "Mesost", "Metton (Cancoillotte)", "Meyer Vintage Gouda",
            "Mihalic Peynir", "Milleens", "Mimolette", "Mine-Gabhar", "Mini Baby Bells", "Mixte",
            "Molbo", "Monastery Cheeses", "Mondseer", "Mont D'or Lyonnais", "Montasio",
            "Monterey Jack", "Monterey Jack Dry", "Morbier", "Morbier Cru de Montagne",
            "Mothais a la Feuille", "Mozzarella", "Mozzarella (Australian)",
            "Mozzarella di Bufala", "Mozzarella Fresh, in water", "Mozzarella Rolls", "Munster",
            "Murol", "Mycella", "Myzithra", "Naboulsi", "Nantais", "Neufchatel",
            "Neufchatel (Australian)", "Niolo", "Nokkelost", "Northumberland", "Oaxaca",
            "Olde York", "Olivet au Foin", "Olivet Bleu", "Olivet Cendre",
            "Orkney Extra Mature Cheddar", "Orla", "Oschtjepka", "Ossau Fermier", "Ossau-Iraty",
            "Oszczypek", "Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer", "Panela",
            "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)", "Parmigiano Reggiano",
            "Pas de l'Escalette", "Passendale", "Pasteurized Processed", "Pate de Fromage",
            "Patefine Fort", "Pave d'Affinois", "Pave d'Auge", "Pave de Chirac", "Pave du Berry",
            "Pecorino", "Pecorino in Walnut Leaves", "Pecorino Romano", "Peekskill Pyramid",
            "Pelardon des Cevennes", "Pelardon des Corbieres", "Penamellera", "Penbryn",
            "Pencarreg", "Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
            "Picodon de Chevre", "Picos de Europa", "Piora", "Pithtviers au Foin",
            "Plateau de Herve", "Plymouth Cheese", "Podhalanski", "Poivre d'Ane", "Polkolbin",
            "Pont l'Eveque", "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
            "Pourly", "Prastost", "Pressato", "Prince-Jean", "Processed Cheddar", "Provolone",
            "Provolone (Australian)", "Pyengana Cheddar", "Pyramide", "Quark",
            "Quark (Australian)", "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
            "Queso Blanco", "Queso Blanco con Frutas --Pina y Mango", "Queso de Murcia",
            "Queso del Montsec", "Queso del Tietar", "Queso Fresco", "Queso Fresco (Adobera)",
            "Queso Iberico", "Queso Jalapeno", "Queso Majorero", "Queso Media Luna",
            "Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette", "Ragusano", "Raschera",
            "Reblochon", "Red Leicester", "Regal de la Dombes", "Reggianito", "Remedou",
            "Requeson", "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata", "Ridder",
            "Rigotte", "Rocamadour", "Rollot", "Romano", "Romans Part Dieu", "Roncal", "Roquefort",
            "Roule", "Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu", "Saaland Pfarr",
            "Saanenkaese", "Saga", "Sage Derby", "Sainte Maure", "Saint-Marcellin",
            "Saint-Nectaire", "Saint-Paulin", "Salers", "Samso", "San Simon", "Sancerre",
            "Sap Sago", "Sardo", "Sardo Egyptian", "Sbrinz", "Scamorza", "Schabzieger", "Schloss",
            "Selles sur Cher", "Selva", "Serat", "Seriously Strong Cheddar", "Serra da Estrela",
            "Sharpam", "Shelburne Cheddar", "Shropshire Blue", "Siraz", "Sirene", "Smoked Gouda",
            "Somerset Brie", "Sonoma Jack", "Sottocenare al Tartufo", "Soumaintrain",
            "Sourire Lozerien", "Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
            "Stilton", "Stinking Bishop", "String", "Sussex Slipcote", "Sveciaost", "Swaledale",
            "Sweet Style Swiss", "Swiss", "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
            "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea", "Testouri",
            "Tete de Moine", "Tetilla", "Texas Goat Cheese", "Tibet", "Tillamook Cheddar",
            "Tilsit", "Timboon Brie", "Toma", "Tomme Brulee", "Tomme d'Abondance",
            "Tomme de Chevre", "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans", "Tommes",
            "Torta del Casar", "Toscanello", "Touree de L'Aubier", "Tourmalet",
            "Trappe (Veritable)", "Trois Cornes De Vendee", "Tronchon", "Trou du Cru", "Truffe",
            "Tupi", "Turunmaa", "Tymsboro", "Tyn Grug", "Tyning", "Ubriaco", "Ulloa",
            "Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco", "Vendomois",
            "Vieux Corse", "Vignotte", "Vulscombe", "Waimata Farmhouse Blue",
            "Washed Rind Cheese (Australian)", "Waterloo", "Weichkaese", "Wellington",
            "Wensleydale", "White Stilton", "Whitestone Farmhouse", "Wigmore", "Woodside Cabecou",
            "Xanadu", "Xynotyro", "Yarg Cornish", "Yarra Valley Pyramid", "Yorkshire Blue",
            "Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"
    };
}
