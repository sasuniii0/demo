package com.example.demo.service;

import com.example.demo.dto.ApiResponseDTO;
import com.example.demo.dto.BankAccountDTO;
import com.example.demo.util.XmlParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CalculatorService {

    private final WebClient webClient;
    private final XmlParser xmlParser;

    public CalculatorService(XmlParser xmlParser) {
        this.xmlParser = xmlParser;
        this.webClient = WebClient.builder()
                .baseUrl("http://www.dneonline.com/calculator.asmx")
                .defaultHeader("Content-Type", "text/xml")
                .defaultHeader("SOAPAction", "http://tempuri.org/Add")
                .defaultHeader("Accept", "text/xml")
                .build();
    }

//    public JSONObject getJsonResponse() {
//        try {
//            String xmlResponse = callSoapService();
//            return XML.toJSONObject(xmlResponse);
//
//        } catch (Exception e) {
//            throw new RuntimeException("SOAP Service Error: " + e.getMessage());
//        }
//    }
//
//    public JSONObject convert(String xml) {
//        try {
//            return XML.toJSONObject(xml);
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid XML format");
//        }
//    }
//
//    public String callSoapService() {
//
//        String xmlRequest = """
//        <?xml version="1.0" encoding="utf-8"?>
//        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
//                       xmlns:tem="http://tempuri.org/">
//           <soap:Body>
//              <tem:Add>
//                 <tem:intA>10</tem:intA>
//                 <tem:intB>5</tem:intB>
//              </tem:Add>
//           </soap:Body>
//        </soap:Envelope>
//        """;
//
//        return webClient.post()
//                .bodyValue(xmlRequest)
//                .retrieve()
//                .bodyToMono(String.class)
//                .timeout(Duration.ofSeconds(5))
//                .onErrorResume(ex -> {
//                    throw new RuntimeException("External SOAP API failed");
//                })
//                .block();
//    }
//
//    public JSONObject process(String xml) {
//
//        JSONObject json = xmlParser.toJson(xml);
//
//        Set<String> allowed = new HashSet<>();
//
//        allowed.add("AcctInqRs");
//        allowed.add("DepAcctId");
//        allowed.add("AcctId");
//        allowed.add("AcctType");
//        allowed.add("Nickname");
//        allowed.add("AcctStatCode");
//        allowed.add("BranchId");
//
//        return JsonFilter.filter(json, allowed);
//    }

    public ApiResponseDTO<?> processBankXml() {

        String xml = "<IFX>\n" +
                "\t<GeneralStatus>\n" +
                "\t\t<StatusCode>0</StatusCode>\n" +
                "\t\t<StatusLevel>IFX</StatusLevel>\n" +
                "\t\t<StatusDesc>The request was processed successfully.</StatusDesc>\n" +
                "\t</GeneralStatus>\n" +
                "\t<Status>\n" +
                "\t\t<StatusCode>0</StatusCode>\n" +
                "\t\t<Severity>Info</Severity>\n" +
                "\t\t<StatusDesc>The request was processed successfully.</StatusDesc>\n" +
                "\t\t<SupportUID>B299BE1F-5AEC-45C2-8B24-A658BD56CED2</SupportUID>\n" +
                "\t</Status>\n" +
                "\t<SignonRs>\n" +
                "\t\t<Status>\n" +
                "\t\t\t<StatusCode>0</StatusCode>\n" +
                "\t\t\t<Severity>Info</Severity>\n" +
                "\t\t\t<StatusDesc>The request was processed successfully.</StatusDesc>\n" +
                "\t\t\t<SupportUID>CF2C4583-503B-4B09-AD7E-AA2087AD0ADB</SupportUID>\n" +
                "\t\t</Status>\n" +
                "\t\t<SignonRole>CSR</SignonRole>\n" +
                "\t\t<CustId>\n" +
                "\t\t\t<SPName>FiservICBS</SPName>\n" +
                "\t\t\t<CustLoginId>SGINTNET</CustLoginId>\n" +
                "\t\t</CustId>\n" +
                "\t\t<GenSessKey>0</GenSessKey>\n" +
                "\t\t<ClientDt>\n" +
                "\t\t\t<Year>2011</Year>\n" +
                "\t\t\t<Month>1</Month>\n" +
                "\t\t\t<Day>27</Day>\n" +
                "\t\t</ClientDt>\n" +
                "\t\t<CustLangPref/>\n" +
                "\t\t<ClientApp>\n" +
                "\t\t\t<Org>Fiserv</Org>\n" +
                "\t\t\t<Name>IB</Name>\n" +
                "\t\t\t<Version>0.1</Version>\n" +
                "\t\t\t<ClientAppKey>BOCSRVRHECGNSYSQCUWRJDHKRFDBNTGN</ClientAppKey>\n" +
                "\t\t</ClientApp>\n" +
                "\t\t<ServerDt>\n" +
                "\t\t\t<Year>2024</Year>\n" +
                "\t\t\t<Month>8</Month>\n" +
                "\t\t\t<Day>2</Day>\n" +
                "\t\t\t<Hour>15</Hour>\n" +
                "\t\t\t<Minute>35</Minute>\n" +
                "\t\t\t<Second>39</Second>\n" +
                "\t\t\t<Fraction>673000</Fraction>\n" +
                "\t\t\t<UTCOffset>330</UTCOffset>\n" +
                "\t\t</ServerDt>\n" +
                "\t\t<Language>en-US</Language>\n" +
                "\t\t<ComputerKey>SGPVMAPRDEV03</ComputerKey>\n" +
                "\t\t<PersonName>\n" +
                "\t\t\t<LastName>User1</LastName>\n" +
                "\t\t\t<FirstName>SGINTNET</FirstName>\n" +
                "\t\t</PersonName>\n" +
                "\t</SignonRs>\n" +
                "\t<EnvironmentInfo>\n" +
                "\t\t<EnvironmentName>Default</EnvironmentName>\n" +
                "\t</EnvironmentInfo>\n" +
                "\t<BankSvcRs>\n" +
                "\t\t<Status>\n" +
                "\t\t\t<StatusCode>0</StatusCode>\n" +
                "\t\t\t<Severity>Info</Severity>\n" +
                "\t\t\t<StatusDesc>The request was processed successfully.</StatusDesc>\n" +
                "\t\t\t<SupportUID>5BFD6249-5A8E-4A96-A46D-9469547E5E33</SupportUID>\n" +
                "\t\t</Status>\n" +
                "\t\t<RqUID>5bfd6249-5a8e-4a96-a46d-9469547e5e33</RqUID>\n" +
                "\t\t<SPName>FiservICBS</SPName>\n" +
                "\t\t<Version>10.1.45.0</Version>\n" +
                "\t\t<AcctInqRs>\n" +
                "\t\t\t<Status>\n" +
                "\t\t\t\t<StatusCode>0</StatusCode>\n" +
                "\t\t\t\t<Severity>Info</Severity>\n" +
                "\t\t\t\t<StatusDesc>The request was processed successfully.</StatusDesc>\n" +
                "\t\t\t\t<SupportUID>83119161-725A-4A8F-9EF1-110730105AE0</SupportUID>\n" +
                "\t\t\t</Status>\n" +
                "\t\t\t<RqUID>6907e242-bc01-4dbe-92c9-7dcb445fdcb6</RqUID>\n" +
                "\t\t\t<CustId>\n" +
                "\t\t\t\t<SPName>FiservCBS</SPName>\n" +
                "\t\t\t\t<CustPermId>0000000312</CustPermId>\n" +
                "\t\t\t</CustId>\n" +
                "\t\t\t<DepAcctId>\n" +
                "\t\t\t\t<AcctId>0000561482</AcctId>\n" +
                "\t\t\t\t<AcctType>SV</AcctType>\n" +
                "\t\t\t</DepAcctId>\n" +
                "\t\t\t<LastTrnDt>\n" +
                "\t\t\t\t<Year>2024</Year>\n" +
                "\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t<Day>28</Day>\n" +
                "\t\t\t</LastTrnDt>\n" +
                "\t\t\t<LastStmtDt>\n" +
                "\t\t\t\t<Year>2022</Year>\n" +
                "\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t<Day>21</Day>\n" +
                "\t\t\t</LastStmtDt>\n" +
                "\t\t\t<DepAcctRec>\n" +
                "\t\t\t\t<LastDepDt>\n" +
                "\t\t\t\t\t<Year>2024</Year>\n" +
                "\t\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t\t<Day>25</Day>\n" +
                "\t\t\t\t</LastDepDt>\n" +
                "\t\t\t\t<LastDepCurAmt>\n" +
                "\t\t\t\t\t<Amt>75000.00</Amt>\n" +
                "\t\t\t\t\t<CurCode>LKR</CurCode>\n" +
                "\t\t\t\t</LastDepCurAmt>\n" +
                "\t\t\t\t<IntPmt>\n" +
                "\t\t\t\t\t<LastIntPmtDt>\n" +
                "\t\t\t\t\t\t<Year>2024</Year>\n" +
                "\t\t\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t\t\t<Day>28</Day>\n" +
                "\t\t\t\t\t</LastIntPmtDt>\n" +
                "\t\t\t\t\t<NextIntPmtDt>\n" +
                "\t\t\t\t\t\t<Year>2024</Year>\n" +
                "\t\t\t\t\t\t<Month>04</Month>\n" +
                "\t\t\t\t\t\t<Day>30</Day>\n" +
                "\t\t\t\t\t</NextIntPmtDt>\n" +
                "\t\t\t\t\t<Freq>EndofMonth</Freq>\n" +
                "\t\t\t\t</IntPmt>\n" +
                "\t\t\t\t<OpenDt>\n" +
                "\t\t\t\t\t<Year>2001</Year>\n" +
                "\t\t\t\t\t<Month>12</Month>\n" +
                "\t\t\t\t\t<Day>04</Day>\n" +
                "\t\t\t\t</OpenDt>\n" +
                "\t\t\t\t<NoticeDt>\n" +
                "\t\t\t\t\t<Year>0000</Year>\n" +
                "\t\t\t\t\t<Month>00</Month>\n" +
                "\t\t\t\t\t<Day>00</Day>\n" +
                "\t\t\t\t</NoticeDt>\n" +
                "\t\t\t\t<ContactDt>\n" +
                "\t\t\t\t\t<Year>2024</Year>\n" +
                "\t\t\t\t\t<Month>04</Month>\n" +
                "\t\t\t\t\t<Day>04</Day>\n" +
                "\t\t\t\t</ContactDt>\n" +
                "\t\t\t\t<PrevOneStmtDt>\n" +
                "\t\t\t\t\t<Year>2022</Year>\n" +
                "\t\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t\t<Day>18</Day>\n" +
                "\t\t\t\t</PrevOneStmtDt>\n" +
                "\t\t\t\t<PrevTwoStmtDt>\n" +
                "\t\t\t\t\t<Year>2022</Year>\n" +
                "\t\t\t\t\t<Month>03</Month>\n" +
                "\t\t\t\t\t<Day>09</Day>\n" +
                "\t\t\t\t</PrevTwoStmtDt>\n" +
                "\t\t\t\t<HoldAmt>\n" +
                "\t\t\t\t\t<Amt>4000.00</Amt>\n" +
                "\t\t\t\t</HoldAmt>\n" +
                "\t\t\t\t<FloatAmt>\n" +
                "\t\t\t\t\t<Amt>0.00</Amt>\n" +
                "\t\t\t\t</FloatAmt>\n" +
                "\t\t\t\t<ODUsageLimitAmt>\n" +
                "\t\t\t\t\t<Amt>0.00</Amt>\n" +
                "\t\t\t\t</ODUsageLimitAmt>\n" +
                "\t\t\t</DepAcctRec>\n" +
                "\t\t\t<AcctStatCode>Open</AcctStatCode>\n" +
                "\t\t\t<ProdId>00003</ProdId>\n" +
                "\t\t\t<BranchId>571</BranchId>\n" +
                "\t\t\t<RelationCode>SOW</RelationCode>\n" +
                "\t\t\t<Nickname>18+YOUTH SAVINGS</Nickname>\n" +
                "\t\t\t<AcctShortName>MR S S WICKRAMASINGHA</AcctShortName>\n" +
                "\t\t\t<AcctTitle/>\n" +
                "\t\t</AcctInqRs>\n" +
                "\t</BankSvcRs>\n" +
                "\t<AcctBal>\n" +
                "\t\t<BalType>Current</BalType>\n" +
                "\t\t<CurAmt>\n" +
                "\t\t\t<Amt>0.00</Amt>\n" +
                "\t\t\t<CurCode>LKR</CurCode>\n" +
                "\t\t</CurAmt>\n" +
                "\t</AcctBal>\n" +
                "\t<AcctBal>\n" +
                "\t\t<BalType>Avail</BalType>\n" +
                "\t\t<CurAmt>\n" +
                "\t\t\t<Amt>0.00</Amt>\n" +
                "\t\t\t<CurCode>LKR</CurCode>\n" +
                "\t\t</CurAmt>\n" +
                "\t</AcctBal>\n" +
                "</IFX>";

        JSONObject json = xmlParser.toJson(xml);

        JSONObject acctInqRs = xmlParser.findObject(json, "AcctInqRs");

        if (acctInqRs == null) {
            throw new RuntimeException("AcctInqRs not found in response");
        }
        BankAccountDTO dto = mapToDto(acctInqRs);

        return new ApiResponseDTO<>(
                "0000",
                "SUCCESS",
                "",
                dto
        );
    }

    private BankAccountDTO mapToDto(JSONObject acctInqRs) {

        BankAccountDTO dto = new BankAccountDTO();

        JSONObject depAcctId = acctInqRs.optJSONObject("DepAcctId");

        if (depAcctId != null) {
            dto.setAccountId(depAcctId.optString("AcctId"));
            dto.setAccountType(depAcctId.optString("AcctType"));
        }

        dto.setNickname(acctInqRs.optString("Nickname"));
        dto.setStatus(acctInqRs.optString("AcctStatCode"));
        dto.setBranchId(acctInqRs.optInt("BranchId"));

        return dto;
    }
}
