package demo.calendar.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import demo.calendar.model.Calendar;
import demo.calendar.model.SendNotice;
import demo.calendar.model.User;
import demo.calendar.repository.CalendarRepository;
import demo.calendar.service.impl.CalendarSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("calendars")
public class CalendarController {

    private JavaMailSender emailSender;
    private CalendarRepository calendarRepository;

    public CalendarController(CalendarRepository calendarRepository
            , JavaMailSender sender
    ) {
        this.calendarRepository = calendarRepository;
        this.emailSender = sender;
    }


//    @GetMapping
//    public String list(ModelMap model, @RequestParam Optional<String> message,
//                       @PageableDefault(size = 1, sort = "createddate", direction = Sort.Direction.ASC) Pageable pageable,
//                       HttpSession session) {
//        Page<Calendar> pages = calendarRepository.findAll(pageable);
//        if (message.isPresent()) {
//            model.addAttribute("message", message.get());
//        }
//        List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
//        if (sortOrders.size() > 0) {
//            Sort.Order order = sortOrders.get(0);
//            model.addAttribute("sort", order.getProperty() + "," + (order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
//        } else {
//            model.addAttribute("sort", "createddate");
//        }
//
//        User x = (User) session.getAttribute("account");
//        if (x != null) {
//            model.addAttribute("account", x);
//        }
//        model.addAttribute("listCalendar", pages);
//        return "list";
//    }

    @GetMapping
    public String list(ModelMap model, @RequestParam Optional<String> message,
                       @PageableDefault(size = 2, sort = "createddate",direction = Sort.Direction.DESC ) Pageable pageable,
                           @RequestParam(required = false)String title,
                           @RequestParam(required = false)String owner,
                           @RequestParam(required = false)String location,
                           @RequestParam(required = false)String type) {
        //setting
        if(title==null){
            title="";
        }

        if(location==null){
            location="";
        }
        if(type==null){
            type="";
        }
        if(owner==null){
            owner="";
        }
        pageable=PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort().descending());
        Specification condition= hasTitle(title).and(hasLocation(location).and(hasType(type).and(hasOwner(owner))));
        Page<Calendar> pages=calendarRepository.findAll(condition,pageable);

        //send message to page
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
        }
        List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0) {
            Sort.Order order = sortOrders.get(0);
//            model.addAttribute("sort", order.getProperty() + "," + (order.getDirection() == Sort.Direction.DESC ? "desc" : "acs"));
            model.addAttribute("sort",order.getProperty());
        } else {
            model.addAttribute("sort", "createddate");
        }
        model.addAttribute("listCalendar", pages);
        model.addAttribute("location", location);
        model.addAttribute("type",type);
        model.addAttribute("owner",owner);
        model.addAttribute("title",title);
        System.out.println(model.getAttribute("sort"));

        return "list";
    }


    @PostMapping("/dlink/v1/push")
    @ResponseBody
    public SendNotice sendNoti(@RequestParam String message,
                               @RequestParam String receiver,
                               @RequestParam String sender,
                               @RequestParam String service,
                               Calendar calendar){
        SendNotice sendNotice=new SendNotice();
        sendNotice.setMessage(message);
        sendNotice.setReceiver(receiver);
        sendNotice.setService(service);
        sendNotice.setCreationDate(new Date());
        return sendNotice;
    }

    @GetMapping("create")
    public String createOrUpdate(ModelMap model) {
        model.addAttribute("calendar", new Calendar());
        return "create";
    }

    @PostMapping("save")
    public String save(RedirectAttributes attributes,
                       @Valid Calendar calendar,
                       BindingResult result,
                       ModelMap model) throws ParseException {
        if (result.hasErrors()) {
            model.addAttribute("calendar", calendar);
            return "create";
        }
        Date date = new Date();
        calendar.setCreateddate(date);
        calendarRepository.save(calendar);
        attributes.addAttribute("message", "Add Successfull");

        //execute email send
//        SimpleMailMessage message = new SimpleMailMessage();
//        String []listEmails=calendar.getReceiver().split(",");
//        for (int i = 0; i < listEmails.length; i++) {
//            //Send to
//            message.setTo(listEmails[i]);
//            //Subject email
//            message.setSubject(calendar.getTitle());
//            //Text in email
//            message.setText(calendar.getFullmsg());
//            //execute send
//            emailSender.send(message);
//        }
        return "redirect:/calendars";
    }

//    @GetMapping("delete/{id}")
    @DeleteMapping("delete/{id}")
    public String remove(RedirectAttributes attributes,
                         @PathVariable Long id) {
        Calendar calendar = calendarRepository.getById(id);
        calendarRepository.delete(calendar);
        attributes.addAttribute("message", "Delete Successfull");
        return "redirect:/calendars";
    }

    @GetMapping("update/{id}")
    public String createOrUpdate(ModelMap model,
                                 @PathVariable(name = "id", required = false) Long id) {
        Calendar calendar = calendarRepository.getById(id);
        model.addAttribute("calendar", calendar);
        return "update";
    }
    @GetMapping("detail/{id}")
    public String detail(ModelMap model,
                                 @PathVariable(name = "id", required = false) Long id) {
        Calendar calendar = calendarRepository.getById(id);
        model.addAttribute("item", calendar);
        return "info";
    }


    //setting
    public static Specification<Calendar> hasType(String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("type"), "%" + type + "%"));
    }

    public static Specification<Calendar> hasLocation(String location) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("location"), "%" + location + "%"));
    }

    public static Specification<Calendar> hasOwner(String owner) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("owner"), "%" + owner + "%"));
    }

    public static Specification<Calendar> hasTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
    }

}
