/Users/user/eclipse.platform.ui/bundles/org.eclipse.ui.workbench/Eclipse UI/org/eclipse/ui/internal/layout/CellLayout.java
org eclipse internal layout java util array list java util list org eclipse swt org eclipse swt graphics point org eclipse swt graphics rectangle org eclipse swt widgets composite org eclipse swt widgets control org eclipse swt widgets layout instance lay control children code composite code grid simple set rules simple this intended predictable code grid layout code easier code form layout code retaining power power code cell layout code lies ability control size resizing properties row column unlike layout classes complex layouts created attaching layout data individual controls layout subclasses code column info code create columns fixed width columns width computed child controls width grows proportion size columns layouts code column info code set size column properties explicitly set this creating layouts columns properties similarly subclasses code row info code control height individual rows for finer grain control code cell data code objects attached individual controls layout these objects serve function code grid data code objects serve code grid layout code they controls span multiple rows columns set justification control cell user override preferred size control cases attach layout data controls layout controls arranged based properties rows columns however layout data attached individual controls span multiple columns control justification cell all code set code methods code code allowing layout created initialized single code for code composite control composite parent control set layout cell layout set margins set spacing code cell layout layout object compute height rows properties explicitly set row row settings row false object compute width columns properties explicitly set row col settings row true horizontal spacing specifies number pixels edge cell left edge neighbouring cell horizontal spacing vertical spacing specifies number pixels bottom edge cell top edge neighbouring cell vertical spacing margin width specifies number pixels horizontal margin left edges layout margin width margin height specifies number pixels vertical margin top bottom edges layout margin height number columns layout indicating layout single row num cols list column info nth object compute width nth column null indicating column list cols list row info nth object compute height nth row null indicating row list rows array list cached grid info grid info grid info cached row min null cached col min null cache misses cache hits layout cache cache layout cache creates layout param num cols number columns layout indicating layout row cell layout num cols num cols num cols cols array list num cols num cols sets amount empty space cells param spacing point number pixels empty space adjacent columns rows cell layout set spacing horizontal spacing vertical spacing horizontal spacing horizontal spacing vertical spacing vertical spacing sets amount empty space cells param spacing point number pixels empty space adjacent columns rows cell layout set spacing point spacing horizontal spacing spacing vertical spacing spacing returns amount empty space adjacent cells point number pixels empty space adjacent columns rows point spacing point horizontal spacing vertical spacing sets size margin layout param margin width size margin top bottom layout param margin height size margin left layout cell layout set margins margin width margin height margin width margin width margin height margin height sets size margin layout param margins point indicating size horizontal vertical margins pixels cell layout set margins point margins margin width margins margin height margins returns size margins layout size outer margins pixels point margins point margin width margin height sets column settings all columns settings explicitly assigned custom settings set column param info properties columns set column cell layout set default column row info col settings info sets column info column number leftmost column column this replaces existing info column note column allowed share column info instance identical properties param col num column number modify param info properties column null column properties cell layout set column col num row info cols size col num cols add null cols set col num info sets row settings layout unless overridden individual row rows settings param info row info object set size rows cell layout set default row row info row settings info sets row info rows topmost row row multiple rows allowed share row info instance param row num row number set param info row info control sizing row null row settings layout cell layout set row row num row info rows size row num rows add null rows set row num info returns row info controls size row will row settings layout custom row info assigned row param row num row row row num horizontal horizontal row num rows size row settings row result row rows row num result null result row settings result row num cols size col settings row result row cols row num result null result col settings result initializes grid info object param children controls layed init grid control children cache set controls children grid info init grid children cached row min null cached col min null override point compute size composite composite hint hint flush cache control children composite children init grid children flush cache cache flush determine amount whitespace area controls point empty space total empty space height constraints compute constraints true width hint width preferred size height constraints false width hint empty space height hint hint height preferred size compute sizes height constraints width false true height hint empty space point preferred size point width empty space height empty space point layout preferred size now adjust smaller minimum size composite dimension preferred size smaller minimum size composite set dimension minimum size recompute dimension increasing width match shell minimum width reduce height allocated wrapping text widget there point dimensions smaller composite minimum size smaller point minimum size cell layout util compute minimum size composite wider preferred size minimum size taller preferred size minimum size wider taller size preferred size recompute preferred width minimum height compute size composite hint minimum size false taller recompute preferred height minimum width compute size composite minimum size hint false minimum size preferred size minimum size compute sizes constraints space computing rows result compute min sizes constraints computing rows total fixed sum sizes result denominator resize denominator computing rows num rows grid info num rows computing rows total fixed space remaining space total fixed idx idx num rows denominator idx row row row idx computing rows row grows greed row size amount remaining greed denominator result idx amount remaining amount denominator greed result computes dimension preferred size layout param hint result computed param constraints constraints dimension for computing preferred row sizes array column sizes param computing rows true method returns height pixels otherwise returns width pixels preferred size constraints computing rows fixed sizes compute min sizes constraints computing rows sum sizes fixed sizes dynamic size constraints fixed sizes computing rows computes sum integers array entries result sum sizes input sum sizes input input length sum sizes input start length sum idx start idx start length idx input idx sum sum returns preferred dynamic width layout param constraints param fixed sizes param computing rows dynamic size constraints fixed sizes computing rows result numerator resize denominator computing rows resizable columns numerator row spacing computing rows vertical spacing horizontal spacing col spacing computing rows horizontal spacing vertical spacing num controls grid info controls length idx idx num controls idx control row start grid info start pos idx computing rows control row span span idx computing rows control col start grid info start pos idx computing rows control col span span idx computing rows denominator growth ratio control row start control row span computing rows denominator width hint sum sizes constraints control col start control col span width hint width hint col spacing control col span compute total control size control size compute control size idx width hint computing rows subtract amount overlaps fixed size columns control size sum sizes fixed sizes control row start control row span subtract amount overlaps spacing cells control size row spacing control row span result math max result control size numerator denominator result computes dimension control size param control control computed param constraint dimension control size unknown param computing height true method returns height else returns width preferred height width control pixels compute control size control constraint computing height cell data data grid info cell data control preferred size control hints constraint point result data compute size cache cache control return result computing height result result compute height computing height data compute size cache cache control constraint data compute size cache cache control constraint returns relative amount control starting row spanning length contribute param start param length param computing rows growth ratio start length computing rows grow false sum start length idx start idx idx row row row idx computing rows row larger than children row grows grow true sum row size grow sum compute min sizes constraints computing rows cache result function called single size computation result computing rows cached row min cached col min result null column spacing row spacing computing rows column spacing horizontal spacing row spacing vertical spacing column spacing vertical spacing row spacing horizontal spacing row count grid info num rows computing rows result row count col count grid info num rows computing rows row controls col count growing row idx idx row count idx row row row idx computing rows row grows there minimum size growing rows growing row idx result idx result idx row size row larger than children determine controls row grid info row row controls idx computing rows col idx col idx row controls length col idx control row controls col idx row method insert empty cells skip control control start grid info start pos control computing rows control span span control computing rows control ends row span growing rows control start control span idx control start growing row control col start grid info start pos control computing rows control col span span control computing rows control row span span control computing rows compute width constraint control spanned width sum sizes constraints control col start control col span spanned width spanned width column spacing control span control height compute control size control spanned width computing rows determine control spans allocated columns allocated height sum sizes result control col start control row span row spacing control row span result idx math max result idx control height allocated height cache result computing rows cached row min result cached col min result result returns height constraints computing column widths requires init grid called param result will height constraint row ith position array constraint row compute constraints horizontal initialize height constraints row basically rows type fixed row constant height num rows grid info num rows horizontal result num rows idx idx num rows idx row row row idx horizontal row grows row larger than children result idx row size result idx result computes total greediness rows total greediness rows resize denominator horizontal result num rows grid info num rows horizontal idx idx num rows idx row row row idx horizontal row grows result row size result computes total fixed height rows param width constraints array nth entry width nth column width unknown total fixed height rows minimum size constraints horizontal control controls control grid info rows result num rows grid info rows idx idx num rows idx result row idx fixed height grid info width constraints idx result span control row cell data data grid info cell data control row data vertical span data horizontal span returns total space required margins spacing cells init grid called point total empty space num rows grid info rows point margin width grid info cols horizontal spacing margin height num rows vertical spacing returns absolute positions row start position row sizes row spacing param start pos position initial row param sizes array row sizes pixels param spacing space row pixels array row positions result size sizes length entry position layout compute row positions start pos sizes spacing result sizes length result start pos idx idx sizes length idx result idx result idx sizes idx spacing result override layout composite composite flush cache control children composite children children children length init grid children flush cache cache flush point empty space total empty space compute area controls margins spacing removed width composite client area width empty space height composite client area height empty space heights compute constraints true widths grid info cols compute actual column widths widths compute sizes heights width false compute actual row heights based actual column widths heights compute sizes widths height true rectangle current cell rectangle starty compute row positions composite client area margin height heights vertical spacing startx compute row positions composite client area margin width widths horizontal spacing num children grid info controls length control control num children control cell data data grid info cell data control row grid info control row control col grid info control col control current cell startx col current cell width startx col data horizontal span current cell horizontal spacing current cell starty row current cell height starty row data vertical span current cell vertical spacing data position control cache cache control current cell columns num cols grow composite composite horizontally init grid composite children num rows grid info num rows horizontally idx idx num rows idx row row row idx horizontally row grows true false