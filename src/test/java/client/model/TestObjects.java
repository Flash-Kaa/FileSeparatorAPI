package client.model;

import java.io.File;

public class TestObjects {
    public static TestParams TEST_OBJ_1
            = new TestParams(
            new File("src/test/resources/test.txt"),
            "Раздел 1 (1)\n" +
                    "\tПодраздел 1 (2)\n" +
                    "\tПодраздел 2 (7)\n" +
                    "\t\tПод-подраздел 1 (8)\n" +
                    "\t\tПод-подраздел 2 (10)\n" +
                    "\t\tПод-подраздел 3 (11)\n" +
                    "Раздел 2 (16)\n" +
                    "Раздел 3 (18)\n" +
                    "\tПодраздел 1 (19)\n" +
                    "\tПодраздел 2 (23)\n" +
                    "\t\tПод-подраздел 1 (27)\n" +
                    "\t\tПод-подраздел 2 (28)\n" +
                    "\tПодраздел 3 (30)\n" +
                    "Раздел 4 (32)\n" +
                    "Раздел 5 (35)\n"
    );

    public static TestParams TEST_OBJ_2
            = new TestParams(
            new File("src/test/resources/test1.txt"),
            ""
    );
    public static TestParams TEST_OBJ_3
            = new TestParams(
            new File("src/test/resources/test2.txt"),
            ""
    );

    public static TestParams TEST_OBJ_4
            = new TestParams(
            new File("src/test/resources/test3.txt"),
            "Раздел 1 ##Подраздел 1 ##Подраздел 2 ###Под-подраздел 1 ###Под-подраздел 2 ###Под-подраздел 3 (1)\n"
    );
}
