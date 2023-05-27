package org.com.zlk.chxg.equity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/23 13:04
 */
public class FilterEquity {

    public static void main(String[] args) {
        String f1 = "{\n" +
                "  \"realName\": \"chxg\",\n" +
                "  \"paramList\": [\n" +
                "    {\n" +
                "      \"creditCardNo\": \"1\",\n" +
                "      \"cardBin\": \"1\",\n" +
                "      \"org\": \"1\",\n" +
                "      \"cardlevel\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"creditCardNo\": \"2\",\n" +
                "      \"cardBin\": \"2\",\n" +
                "      \"org\": \"2\",\n" +
                "      \"cardlevel\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"creditCardNo\": \"3\",\n" +
                "      \"cardBin\": \"3\",\n" +
                "      \"org\": \"3\",\n" +
                "      \"cardlevel\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"creditCardNo\": \"4\",\n" +
                "      \"cardBin\": \"4\",\n" +
                "      \"org\": \"4\",\n" +
                "      \"cardlevel\": \"4\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        RightInterestVO rightInterestVO = JSONObject.parseObject(f1, RightInterestVO.class);
        List<CreditCardParam> paramList = rightInterestVO.getParamList();

        String f2 = "[\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"\",\n" +
                "      \"1\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"2\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"3\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"4\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"4\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"4\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"3\",\n" +
                "      \"4\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"4\",\n" +
                "      \"3\"\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        String f3 = "[\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"4\",\n" +
                "      \"0\"\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"0\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"0\",\n" +
                "      \"4\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "      \"\",\n" +
                "      \"0\"\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "      \"\"\n" +
                "    ]\n" +
                "  }\n" +
                "]";


        String f4 = "[\n" +
                "  {\n" +
                "    \"prodNo\": [\n" +
                "    ],\n" +
                "    \"cardBin\": [\n" +
                "    ],\n" +
                "    \"cardLevel\": [\n" +
                "      \"\"\n" +
                "    ],\n" +
                "    \"cardOrganization\": [\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        List<PublicRightInterestsVO> publicRightInterestsVOList = JSONObject.parseArray(f2, PublicRightInterestsVO.class);
//        List<PublicRightInterestsVO> publicRightInterestsVOList = JSONObject.parseArray(f3, PublicRightInterestsVO.class);
//        List<PublicRightInterestsVO> publicRightInterestsVOList = JSONObject.parseArray(f4, PublicRightInterestsVO.class);

        List<PublicRightInterestsVO> filterList = paramList.parallelStream().flatMap(card -> publicRightInterestsVOList.parallelStream().
                filter(pvo -> pvo.getProdNo().stream().anyMatch(prodno -> prodno.equals(card.getCreditCardNo())) ||
                        pvo.getCardBin().stream().anyMatch(bin -> bin.equals(card.getCardBin())) ||
                        (pvo.getCardLevel().stream().anyMatch(ccl -> ccl.equals(card.getCardlevel())) &&
                                pvo.getCardOrganization().stream().anyMatch(org -> org.equals(card.getOrg()))))).
                distinct().sorted(Comparator.comparingInt(publicRightInterestsVOList::indexOf)).collect(Collectors.toList());

        System.out.println(JSONArray.toJSONString(filterList));

    }
}
