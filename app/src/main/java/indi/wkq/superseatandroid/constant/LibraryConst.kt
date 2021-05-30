package indi.wkq.superseatandroid.constant

/**
 * @author  calesq
 * @date    2021/5/28
 */
object LibraryConst {
    /**
     * 信息分馆房间信息
     */
    private val XINXI_ROOMS =
        mutableMapOf(
            "一楼 3C创客空间" to "4",
            "一楼 3C创客-咖啡区" to "115",
            "一楼 3C创客-电子阅读" to "13",
            "一楼 3C创客-双屏电脑" to "14",
            "一楼 创新学习-MAC电脑" to "15",
            "一楼 创新学习-云桌面" to "16",
            "一楼 创新学习-讨论区" to "90",
            "一楼 创新学习-沙发区" to "118",
            "二楼 西自然科学区" to "6",
            "二楼 东自然科学区" to "7",
            "二楼 中厅沙发区" to "116",
            "三楼 西社会科学区" to "8",
            "三楼 东社会科学区" to "10",
            "三楼 自主学习区" to "12",
            "三楼 中厅沙发区" to "117",
            "四楼 西图书阅览区" to "9",
            "四楼 东图书阅览区" to "11"
        )

    /**
     * 工学分馆房间信息
     */
    private val GONGXUE_ROOMS =
        mutableMapOf(
            "201 自科图书区" to "19",
            "2楼走廊" to "29",
            "205 电阅PC区" to "30",
            "205 电阅笔记本区" to "31",
            "301东 自科借阅区" to "32",
            "305中 自科借阅区" to "33",
            "401东 自科借阅区" to "34",
            "405中 期刊阅览区" to "35",
            "501东 外文借阅区" to "37",
            "505中 自科借阅区" to "38",
            "205 电阅云桌面区" to "68",
            "103 空间双屏云桌面区" to "93",
            "一楼中部走廊" to "100",
            "103 支点空间" to "101",
            "307室" to "110",
            "407室" to "112"
        )

    /**
     * 医学分馆房间信息
     */
    private val YIXUE_ROOMS =
        mutableMapOf(
            "202 中文科技A区" to "18",
            "204 教学参考区" to "20",
            "302 中文科技B区" to "21",
            "305 科技期刊区" to "23",
            "402 中文文科区" to "24",
            "405 电子阅览室" to "25",
            "502 外文区" to "26",
            "506 医学人文区" to "27"
        )

    /**
     * 总馆房间信息
     */
    private val ZONG_ROOMS =
        mutableMapOf(
            "A1 座位区" to "39",
            "A2 借阅区" to "51",
            "A3" to "52",
            "A4" to "60",
            "A5" to "61",
            "A1 沙发区" to "62",
            "A3 电子阅览区" to "63",
            "B2 现场选座" to "77",
            "B3 现场选座" to "78",
            "E2 报刊阅览区" to "84",
            "E4 港台文献阅览区" to "86",
            "E5 地方文献阅览区" to "87",
            "E6 影印文献阅览区" to "88",
            "E2 大厅" to "89",
            "E1 信息共享空间" to "91",
            "E1 信息共享空间双屏云电脑" to "92",
            "B2 自习区" to "94",
            "B4 现场选座" to "95",
            "C1 现场选座" to "96",
            "C2 现场选座" to "97",
            "C3 现场选座" to "98",
            "C4 现场选座" to "99",
            "A2 多媒体双屏电脑区" to "102",
            "A2 多媒体苹果电脑区" to "103",
            "A2 多媒体视频工作区" to "104",
            "B1" to "107",
            "北门走廊" to "113",
            "E7 当代艺术文献中心" to "114",
            "B1 第二培训室" to "120",
            "E1 文献展示区" to "121",
            "E3 学位论文阅览区" to "122",
            "B1 共享空间板凳(无桌)" to "123",
            "A1 手提电脑区" to "124"
        )

    /**
     * 图书馆信息
     */
    val BUILDINGS: Map<String, String> = mapOf(
        "总馆" to "4",
        "信息馆" to "1",
        "工学分馆" to "2",
        "医学分馆" to "3"
    )

    val LIBRARIES: Map<String, MutableMap<String, String>> =
        mapOf(
            "信息馆" to XINXI_ROOMS,
            "工学分馆" to GONGXUE_ROOMS,
            "医学分馆" to YIXUE_ROOMS,
            "总馆" to ZONG_ROOMS
        )
}