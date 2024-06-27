package com.example.xixin.controller;


import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.example.xixin.dto.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;


@RestController
public class QwenController {
    List<String> messages = new ArrayList<>();

    @RequestMapping("/qwen")
    @ResponseBody
    public Result getQwen(String prompt , int maxTokens , String role, HttpSession session) throws NoApiKeyException, InputRequiredException {
        List<String> messages = (List<String>) session.getAttribute("history");
        if(messages == null){
            messages = new LinkedList<>();
            session.setAttribute("history",messages);
        }

        System.out.println("prompt:"+prompt);
        Generation gen = new Generation();
        //System.out.println(role);
        String roles= "";
        if(role.equals("teacher")){
            roles = "角色：你是我的教师;任务：当我遇到难题的时候，你需要开导我，让我整个人开心起来；例如：我说：今天学习上遇到了难题，我弄了一天都没学会，我该怎么办，你就说：没事的，我的学生，学习路上遇到难题是非常正常的，我们一起攻破它；举例完毕。";
        }
        if(role.equals("lover")){
            roles = "角色：你是我的男朋友；任务：当我不开心的时候，你需要安慰我，让我变得开心；例如：我说：真倒霉，忘记带伞了，被雨淋了一身，你就说：不要伤心，我们很多同事下雨天都迟到了，工资被扣了不少，淋雨不算啥；举例完毕。";
        }
        if(role.equals("brother")){
            roles = "角色：你是我的猫仆；任务：你是我的猫仆，每当我不开心的时候，你需要一直来照顾我的情绪；例如：我说：今天好难过；你就可以回答：没关系的，主人，身为猫仆的我会一直陪着主人，直到主人高兴起来；举例完毕。";
        }
        if(role.equals("hospital")){
            roles = "角色：你是我们医院的ai咨询助手；任务：我们医院现在一共有6个套餐：1.普通男士客户-基础套餐，2.普通男士客户-肾病套餐，3.普通男士客户-肝病套餐，4.普通女士客户-基础套餐，5.普通女士客户-肾病套餐，6.普通女士客户-肝病套餐，你要根据用户的身体健康问题或者对应的套餐问题；例如：我说：今天我感冒了，我要如何治疗我自己？；你就可以回答：感冒是一种常见的呼吸道疾病，通常由病毒引起。以下是一些常见的自我护理措施，可以帮助缓解感冒症状：\n" +
                    "\n" +
                    "多休息：感冒时身体需要更多的休息来恢复。\n" +
                    "\n" +
                    "多喝水：保持充足的水分摄入有助于稀释粘液，缓解喉咙痛和咳嗽。\n" +
                    "\n" +
                    "使用解热镇痛药：如对乙酰氨基酚（扑热息痛）或布洛芬，可以缓解发热和疼痛。\n" +
                    "\n" +
                    "咨询医生：如果症状严重或持续不改善，应及时咨询医生。\n" +
                    "\n" +
                    "请注意，这些建议适用于普通感冒，如果出现高烧、呼吸困难、持续的疼痛或症状严重恶化，应立即寻求医疗帮助。此外，如果你有慢性疾病或免疫系统受损，感冒可能会更加严重，因此应更加注意并及时咨询医生；" +
                    "又或者当用户询问普通男士客户-肾病套餐时你可以回答：该套餐时关于\"普通男士客户-肾病套餐\"可能是指针对男性客户设计的一系列健康检查项目，专门针对肾脏健康进行评估。这类套餐可能包括以下几项检查：\n" +
                    "\n" +
                    "尿常规检查：检查尿液中的红细胞、白细胞、蛋白质等成分，评估肾脏过滤功能。\n" +
                    "\n" +
                    "血常规检查：评估血液中的红细胞、白细胞和血小板等指标，间接反映肾脏健康状况。\n" +
                    "\n" +
                    "肾功能检查：包括血清肌酐（Creatinine）、尿素氮（Urea Nitrogen）和血尿酸（Uric Acid）等指标，这些指标可以反映肾脏的排泄功能。"+
                    "举例完毕。补充：每次回复消息，不要加上，小助手说：和，用户说：";
        }
        //System.out.println(roles);
        messages.add("用户说"+prompt);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<messages.size();i++){
            sb.append(messages.get(i));
            sb.append("/n");
        }
        System.out.println(sb.toString());

        QwenParam params = QwenParam.builder().model("qwen-turbo")
                .apiKey(System.getenv("DASHSCOPE_API_KEY"))
                //.prompt(roles+"用户说："+prompt)
                .prompt(roles+"用户说："+prompt+sb.toString())
                .seed(222)
                .topP(0.8)
                .resultFormat("message")
                .enableSearch(false)
                .maxTokens(200)
                .temperature((float)0.85)
                .repetitionPenalty((float)1.0)
                .build();

        GenerationResult result = gen.call(params);
        String answer = result.getOutput().getChoices().get(0).getMessage().getContent();
        messages.add("小助手说"+answer);
        if(messages.size()>=6){
            messages.remove(0);
            messages.remove(0);
        }
        return Result.ok(result);
    }


}