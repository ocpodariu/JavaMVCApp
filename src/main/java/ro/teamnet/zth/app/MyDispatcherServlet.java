package ro.teamnet.zth.app;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.controller.DepartmentController;
import ro.teamnet.zth.app.controller.EmployeeController;
import ro.teamnet.zth.app.controller.JobController;
import ro.teamnet.zth.app.controller.LocationController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
public class MyDispatcherServlet extends HttpServlet {

    private Map<String, MethodAttributes> allowedMethods = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.app.controller");

            getAllowedRequestMethods(classes);
            System.out.println(allowedMethods);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllowedRequestMethods(Iterable<Class> classes) {
        for (Class controller : classes) {
            if (controller.isAnnotationPresent(MyController.class)) {
                MyController controllerAnnotation = (MyController) controller.getAnnotation(MyController.class);
                String controllerUrlPath = controllerAnnotation.urlPath();

                Method[] methods = controller.getMethods();
                for (Method controllerMethod : methods) {
                    if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                        MyRequestMethod methodAnnotation = (MyRequestMethod) controllerMethod.getAnnotation(MyRequestMethod.class);
                        String key = controllerUrlPath + methodAnnotation.urlPath();

                        MethodAttributes methodAttributes = new MethodAttributes();
                        methodAttributes.setControllerClass(controller.getName());
                        methodAttributes.setMethodName(controllerMethod.getName());
                        methodAttributes.setMethodType(methodAnnotation.methodType());

                        allowedMethods.put(key, methodAttributes);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);
    }

    /**
     * Handles all types of requests(GET, POST)
     *
     * @param method type of request (GET / POST)
     * @param req
     * @param resp
     */
    private void dispatchReply(String method, HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Process request
            Object dispatchResult = dispatch(req, resp);

            // Send response to client
            reply(dispatchResult, req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DispatchException e) {
            try {
                sendExceptionError(e, resp);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Delegates tasks to the corresponding Application Controller.
     *
     * @param req
     * @param resp
     * @return
     */
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();

        // Find the controller mapped to this URL
        MethodAttributes methodAttributes = allowedMethods.get(pathInfo);

        // If there's a controller that knows how to process this request
        if (methodAttributes != null) {
            try {
                // Use reflection to find the right class and method
                Class clazz = Class.forName(methodAttributes.getControllerClass());
                Method method = clazz.getMethod(methodAttributes.getMethodName());

                // Create an instance and call the method
                Object applicationController = clazz.newInstance();
                Object result = method.invoke(applicationController, null);

                return result;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        throw new DispatchException();
    }

    private void reply(Object result, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.printf(result.toString());
    }

    private void sendExceptionError(DispatchException de, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.printf("URL-ul dorit nu este mapat!");
    }


}
