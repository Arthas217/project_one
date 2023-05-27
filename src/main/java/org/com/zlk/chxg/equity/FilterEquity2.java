package org.com.zlk.chxg.equity;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/23 13:04
 */
public class FilterEquity2 {

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
        List<PublicRightInterestsVO> publicRightInterestsVOList = JSONObject.parseArray(f2, PublicRightInterestsVO.class);

        Map<PublicRightInterestsVO, CreditCardParam> resultMap = new HashMap<>();
        paramList.stream().forEach(card -> {
            List<PublicRightInterestsVO> filteredList = publicRightInterestsVOList.stream()
                    .filter(getPredicate(card)).distinct().sorted(Comparator.comparingInt(publicRightInterestsVOList::indexOf)).
                            collect(Collectors.toList());
//            filteredList.stream().forEach(po -> resultMap.put(po, card));
            filteredList.forEach(vo -> resultMap.putIfAbsent(vo, card));
        });
        System.out.println(resultMap);


        List<PublicRightInterestsVO> filterList = paramList.parallelStream().flatMap(card -> publicRightInterestsVOList.parallelStream().
                filter(getPredicate(card))).
                distinct().sorted(Comparator.comparingInt(publicRightInterestsVOList::indexOf)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(filterList));

        filterList.stream().forEach(
                pvo->{ CreditCardParam creditCardParam = resultMap.get(pvo);
                }
        );





//        Map<String, List<PublicRightInterestsVO>> publicRightInterestsVOMap =
//                publicRightInterestsVOList.stream().flatMap(p -> Stream.concat(p.getProdNo().stream(), p.getCardBin().stream())
//                                .distinct().map(key -> new AbstractMap.SimpleEntry<>(key, p)))
//                        .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.mapping(AbstractMap.SimpleEntry::getValue,
//                                Collectors.toList())));
//
//        Map<PublicRightInterestsVO, CreditCardParam> filterMap = new HashMap<>();
//        paramList.parallelStream().forEach(card -> {
//            List<PublicRightInterestsVO> filteredList = publicRightInterestsVOMap.getOrDefault(card.getCreditCardNo(), Collections.emptyList());
//            filteredList.addAll(publicRightInterestsVOMap.getOrDefault(card.getCardBin(), Collections.emptyList()));
//            filteredList.stream()
//                    .filter(p -> p.getCardLevel().contains(card.getCardlevel()) && p.getCardOrganization().contains(card.getOrg()))
//                    .forEach(p -> filterMap.computeIfAbsent(p, k -> card));
//        });
//
//
//        System.out.println(filterMap);

    }

    private static Predicate<PublicRightInterestsVO> getPredicate(CreditCardParam card) {
        return pvo -> pvo.getProdNo().stream().anyMatch(prodno -> prodno.equals(card.getCreditCardNo())) ||
                pvo.getCardBin().stream().anyMatch(bin -> bin.equals(card.getCardBin())) ||
//                ((pvo.getCardLevel().stream().anyMatch(ccl -> ccl.equals(card.getCardlevel()))) &&
//                        (pvo.getCardOrganization().stream().anyMatch(org -> org.equals(card.getOrg())))
//                );

                (pvo.getCardLevel().stream().anyMatch(ccl -> ccl.equals(card.getCardlevel())) &&
                        pvo.getCardOrganization().stream().anyMatch(org -> org.equals(card.getOrg()))
                );
    }
}
