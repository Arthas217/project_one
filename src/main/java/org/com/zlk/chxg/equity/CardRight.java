package org.com.zlk.chxg.equity;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/26 11:05
 */
public class CardRight {

    static String f1 = "{\n" +
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

    static String f2 = "[\n" +
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

    private static Predicate<PublicRightInterestsVO> predicateCondition(CreditCardParam cardParam) {
        return publicRightInterestsVO -> publicRightInterestsVO.getCardLevel().stream().anyMatch(cardlevel -> cardlevel.equals(cardParam.getCardlevel()));
    }

    private static <T> List<T> listIntegratiopn(List<T>... lists) {
        List<T> collect = Arrays.stream(lists).flatMap(List::stream).collect(Collectors.toList());
        return collect;
    }

    public static void main(String[] args) {
        RightInterestVO rightInterestVO = JSONObject.parseObject(f1, RightInterestVO.class);
        List<CreditCardParam> paramList = rightInterestVO.getParamList();
        List<PublicRightInterestsVO> publicRightInterestsVOList = JSONObject.parseArray(f2, PublicRightInterestsVO.class);

        //.filter  findFirst
        long index = 0;
        Optional<PublicRightInterestsVO> first = publicRightInterestsVOList.stream().filter(publicRightInterestsVO -> "123".equals(publicRightInterestsVO.getName())).findFirst();
        if (first.isPresent()) {
            System.out.println(first.get().getName());
            index++;
        }
        //skip
        List<PublicRightInterestsVO> skipList = publicRightInterestsVOList.stream().skip(index).collect(Collectors.toList());

        //在collect中过滤保留满足paramlist某些属性的集合
        List<PublicRightInterestsVO> matchList = paramList.parallelStream().flatMap(card -> skipList.parallelStream().filter(predicateCondition(card))).distinct().sorted(Comparator.comparingInt(skipList::indexOf)).collect(Collectors.toList());
        //对匹配满足paramList列表中的cardlevel的集合skipList做映射，规则如果key相同就以第一次put为准
        Map<PublicRightInterestsVO, CreditCardParam> pcMap = new HashMap<>();
        paramList.stream().forEach(card -> {
                    List<PublicRightInterestsVO> filterList = skipList.parallelStream().filter(predicateCondition(card)).distinct().sorted(Comparator.comparingInt(skipList::indexOf)).collect(Collectors.toList());
                    filterList.forEach(vo -> pcMap.putIfAbsent(vo, card));
                }
        );

        //转map
        Map<String, List<String>> stringListMap = skipList.stream().collect(Collectors.toMap(PublicRightInterestsVO::getName, PublicRightInterestsVO::getCardBin, (firsts, second) -> firsts));

        //多个集合合并
        List<PublicRightInterestsVO> mergeList = listIntegratiopn(matchList, skipList);

        //排序
        List<PublicRightInterestsVO> sortList = mergeList.stream().sorted(Comparator.comparing(PublicRightInterestsVO::getName).thenComparing(PublicRightInterestsVO::toString)).collect(Collectors.toList());


    }
}
