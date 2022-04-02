用Map+函数式接口来实现策略模式

例子：
~~~~
@RestController
public class GrantTypeController {

    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @PostMapping("/grantType")
    public String test(String resourceName){
        return queryGrantTypeService.getResult(resourceName);
    }
}
~~~~

~~~~
@Service
public class GrantTypeSerive {

    public String redPaper(String resourceId){
        //红包的发放方式
        return "每周末9点发放";
    }
    public String shopping(String resourceId){
        //购物券的发放方式
        return "每周三9点发放";
    }
    public String QQVip(String resourceId){
        //qq会员的发放方式
        return "每周一0点开始秒杀";
    }
}
~~~~

~~~~

@Service
public class QueryGrantTypeService {
    @Autowired
    private GrantTypeSerive grantTypeSerive;

    private Map<String, Function<String,String>> grantTypeMap=new HashMap<>();

    /**
     * 初始化业务分派逻辑,代替了if-else部分
     * key: 优惠券类型
     *
     * value: lambda表达式,最终会获得该优惠券的发放方式
     * */
    @PostConstruct
    public void dispatcherInit(){
        grantTypeMap.put("红包",resourceId->grantTypeSerive.redPaper(resourceId));
        grantTypeMap.put("购物券",resourceId->grantTypeSerive.shopping(resourceId));
        grantTypeMap.put("qq会员",resourceId->grantTypeSerive.QQVip(resourceId));
    }

    public String getResult(String resourceType){
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Function<String,String> result=grantTypeMap.get(resourceType);
        if(result!=null){
            //传入resourceId 执行这段表达式获得String型的grantType
            return result.apply(resourceType);
        }
        return "查询不到该优惠券的发放方式";
    }
}
~~~~
[代码入口](https://github.com/ZhouFengXun/Fighter/tree/main/fighter-demo/src/main/java/com/fanlan/fighterdemo/designMethod)