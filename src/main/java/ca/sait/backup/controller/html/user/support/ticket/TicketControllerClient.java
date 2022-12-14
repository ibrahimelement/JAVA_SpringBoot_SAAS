package ca.sait.backup.controller.html.mediator.ticket;

import ca.sait.backup.model.entity.SupportTicket;
import ca.sait.backup.model.entity.SupportTicketStatusEnum;
import ca.sait.backup.service.SessionService;
import ca.sait.backup.service.SupportTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/support/ticket")
public class TicketControllerClient {

    @Autowired
    SupportTicketService ticketService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{ticketId}")
    public String GetSpecificTicket(@PathVariable("ticketId") Long ticketId, Model model, HttpServletRequest request) {

        // Expose session variables
        this.sessionService.exposeEssentialVariables(request, model);

        SupportTicket ticket = this.ticketService.mediator_GetTicketById(
                ticketId
        );

        List<SupportTicket> userTickets = this.ticketService.getSupportTicketsForUser(
                ticket.getComplainant()
        );

        model.addAttribute("ticket", ticket);
        model.addAttribute("userTickets", userTickets);

        //ticket.getChat().get(0).getFrom().getName().substring

        return "/user/specific_ticket";
    }



}
