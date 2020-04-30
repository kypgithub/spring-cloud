package com.abugong.cloud.utils.zuul; 

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter; 
import com.netflix.zuul.context.RequestContext;

public class CustomZuulFilter extends ZuulFilter { 
	private static Logger log = LoggerFactory.getLogger(CustomZuulFilter.class);
	/**
	 * 主要用来身份认证
	 * 定义filter的类型，有pre、route、post、error四种
	 */
	@Override  
	public String filterType() {
		return "pre";
	} 
	/**
	 * 执行顺序
	 */
	@Override  
	public int filterOrder() {   
		return 0;  
	}
	/**
	 * 表示是否需要执行该filter，true表示执行，false表示不执行
	 */
	@Override  
	public boolean shouldFilter() {   
		return true; 
	} 
	/**
	 * 执行的方法
	 */
	@Override  
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getHeader("token");
		token = "123456";
		if ("123456".equals(token)) {
			ctx.setSendZuulResponse(true);// 对该请求进行路由
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true);// 设值，可以在多个过滤器时使用
			return null;
		}else {
			ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
			ctx.setResponseStatusCode(403);// 返回错误码
			ctx.setResponseBody("{\"result\":\"Request illegal!the token is null\"}");// 返回错误内容
			ctx.set("isSuccess", false);
		}
		System.out.println("请求路径为：" + request.getRequestURI());
		return null;
	} 

//	@Override
//	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//		
//		// 1. clear userInfo from HTTP header to avoid fraud attack
//		ctx.addZuulRequestHeader("user-info", "");
//		System.out.println("==============");
//		// 2. verify the passed user token
//		String accessToken = request.getHeader(GATE_ACCESSTOKEN);
//		log.info("AccessToken: {}", accessToken);
//		Claims claims = null;
//		if (StringUtils.isNotBlank(accessToken)) {
//			try {
//				claims = TokenUtil.parseJWT(accessToken, GATE_SECRETKEY);
//				if (claims == null) {
//					this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,"Error AccessToken for the API (" + request.getRequestURI().toString() + ")");
//					return null;
//				}
//				System.out.println("claims is:{}"+ claims);
//				System.out.println("claims.getSubject is:{}" + claims.getSubject());
//					
//				String username = claims.getSubject();  //加密的字段，也可以用 claims.get("sub")
//				log.info("userInfo:{}", username);
//				// 3. set userInfo to HTTP header
//				String encodeUserInfo = username;
//				try {
//					encodeUserInfo = URLEncoder.encode(username, "UTF-8");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				ctx.addZuulRequestHeader("user-info", encodeUserInfo);
//				log.info("ZuulRequestHeaders userInfo: {}", ctx.getZuulRequestHeaders().get("user-info"));
//
//				
//			} catch (Exception e) {
//				this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,
//						"Error AccessToken for the API (" + request.getRequestURI().toString() + ")");
//				return null;
//			}
//		}else if ("/auth/login.api".equals(request.getRequestURI().toString())) {
//			return null;
//		}
//		else {
//			this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED, "AccessToken is needed for the API (" + request.getRequestURI().toString() + ")");
//		    return null;
//		}
//		//这个方法的返回值目前没什么作用，我们直接返回null就可以
//		return null;
//	}
	
//	/**
//	 * 中断请求
//	 * @param ctx RequestContext ctx
//	 * @param status 状态
//	 * @param responseText 返回的自定义信息
//	 */
//	private void stopZuulRoutingWithError(RequestContext ctx, HttpStatus status, String responseText) {
//
//		ctx.removeRouteHost();
//		ctx.setResponseStatusCode(status.value());
//		ctx.setResponseBody(responseText);
//		//zuul通过sendfalse来中断请求
//		ctx.setSendZuulResponse(false);
//		
//	}
}