# springboot自带工具类列表详情

### ObjectUtils

1.获取对象的基本信息

// 获取对象的类名。参数为 null 时，返回字符串："null"   
String nullSafeClassName(Object obj)  
// 参数为 null 时，返回 0  
int nullSafeHashCode(Object object)  
// 参数为 null 时，返回字符串："null"  
String nullSafeToString(boolean[] array)  
// 获取对象 HashCode（十六进制形式字符串）。参数为 null 时，返回 0   
String getIdentityHexString(Object obj)  
// 获取对象的类名和 HashCode。 参数为 null 时，返回字符串：""   
String identityToString(Object obj)  
// 相当于 toString()方法，但参数为 null 时，返回字符串：""  
String getDisplayString(Object obj)  

2. 判断工具

// 判断数组是否为空  
**boolean isEmpty(Object[] array)**  
// 判断参数对象是否是数组  
boolean isArray(Object obj)
// 判断数组中是否包含指定元素  
**boolean containsElement(Object[] array, Object element)**  
// 相等，或同为 null时，返回 true  
**boolean nullSafeEquals(Object o1, Object o2)**  
/*  
判断参数对象是否为空，判断标准为：  
   Optional: Optional.empty()  
      Array: length == 0  
CharSequence: length == 0  
 Collection: Collection.isEmpty()  
        Map: Map.isEmpty()  
*/  
**boolean isEmpty(Object obj)**  

3.其他工具方法
// 向参数数组的末尾追加新元素，并返回一个新数组  
<A, O extends A> A[] addObjectToArray(A[] array, O obj)  
// 原生基础类型数组 --> 包装类数组  
Object[] toObjectArray(Object source)  

### StringUtils

字符串判断工具
// 判断字符串是否为 null，或 ""。注意，包含空白符的字符串为非空  
**boolean isEmpty(Object str)**  
// 判断字符串是否是以指定内容结束。忽略大小写  
boolean endsWithIgnoreCase(String str, String suffix)  
// 判断字符串是否已指定内容开头。忽略大小写  
boolean startsWithIgnoreCase(String str, String prefix)   
// 是否包含空白符  
**boolean containsWhitespace(String str)**  
// 判断字符串非空且长度不为 0，即，Not Empty  
**boolean hasLength(CharSequence str)**  
// 判断字符串是否包含实际内容，即非仅包含空白符，也就是 Not Blank  
boolean hasText(CharSequence str)  
// 判断字符串指定索引处是否包含一个子串。  
boolean substringMatch(CharSequence str, int index, CharSequence substring)  
// 计算一个字符串中指定子串的出现次数  
int countOccurrencesOf(String str, String sub)  
字符串操作工具
// 查找并替换指定子串  
String replace(String inString, String oldPattern, String newPattern)  
// 去除尾部的特定字符  
String trimTrailingCharacter(String str, char trailingCharacter)   
// 去除头部的特定字符  
String trimLeadingCharacter(String str, char leadingCharacter)  
// 去除头部的空白符  
String trimLeadingWhitespace(String str)  
// 去除头部的空白符  
String trimTrailingWhitespace(String str)  
// 去除头部和尾部的空白符  
String trimWhitespace(String str)  
// 删除开头、结尾和中间的空白符  
String trimAllWhitespace(String str)  
// 删除指定子串  
String delete(String inString, String pattern)  
// 删除指定字符（可以是多个）  
String deleteAny(String inString, String charsToDelete)  
// 对数组的每一项执行 trim() 方法  
String[] trimArrayElements(String[] array)  
// 将 URL 字符串进行解码  
String uriDecode(String source, Charset charset)  
路径相关工具方法
// 解析路径字符串，优化其中的 “..”   
String cleanPath(String path)  
// 解析路径字符串，解析出文件名部分  
String getFilename(String path)  
// 解析路径字符串，解析出文件后缀名  
String getFilenameExtension(String path)  
// 比较两个两个字符串，判断是否是同一个路径。会自动处理路径中的 “..”   
boolean pathEquals(String path1, String path2)  
// 删除文件路径名中的后缀部分  
String stripFilenameExtension(String path)   
// 以 “. 作为分隔符，获取其最后一部分  
String unqualify(String qualifiedName)  
// 以指定字符作为分隔符，获取其最后一部分  
String unqualify(String qualifiedName, char separator) 

### CollectionUtils

集合判断工具
// 判断 List/Set 是否为空  
**boolean isEmpty(Collection<?> collection)**  
// 判断 Map 是否为空  
**boolean isEmpty(Map<?,?> map)**  
// 判断 List/Set 中是否包含某个对象  
****boolean containsInstance(Collection<?> collection, Object element)****  
// 以迭代器的方式，判断 List/Set 中是否包含某个对象  
boolean contains(Iterator<?> iterator, Object element)  
// 判断 List/Set 是否包含某些对象中的任意一个  
boolean containsAny(Collection<?> source, Collection<?> candidates)  
// 判断 List/Set 中的每个元素是否唯一。即 List/Set 中不存在重复元素  
boolean hasUniqueObject(Collection<?> collection)  
集合操作工具
// 将 Array 中的元素都添加到 List/Set 中  
<E> void mergeArrayIntoCollection(Object array, Collection<E> collection)    
// 将 Properties 中的键值对都添加到 Map 中  
<K,V> void mergePropertiesIntoMap(Properties props, Map<K,V> map)  
// 返回 List 中最后一个元素  
<T> T lastElement(List<T> list)    
// 返回 Set 中最后一个元素  
<T> T lastElement(Set<T> set)   
// 返回参数 candidates 中第一个存在于参数 source 中的元素  
<E> E findFirstMatch(Collection<?> source, Collection<E> candidates)  
// 返回 List/Set 中指定类型的元素。  
<T> T findValueOfType(Collection<?> collection, Class<T> type)  
// 返回 List/Set 中指定类型的元素。如果第一种类型未找到，则查找第二种类型，以此类推  
Object findValueOfType(Collection<?> collection, Class<?>[] types)  
// 返回 List/Set 中元素的类型  
Class<?> findCommonElementType(Collection<?> collection)  