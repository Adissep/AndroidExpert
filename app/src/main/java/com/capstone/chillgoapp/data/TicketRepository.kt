package com.capstone.chillgoapp.data

import com.capstone.chillgoapp.model.OrderTicket
import com.dicoding.jetticketing.model.FakeTicketDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TicketRepository {

    private val orderTickets = mutableListOf<OrderTicket>()

    init {
        if (orderTickets.isEmpty()) {
            FakeTicketDataSource.dummyTickets.forEach {
                orderTickets.add(OrderTicket(it, 0))
            }
            FakeTicketDataSource.dummyBestTicket.forEach {
                orderTickets.add(OrderTicket(it, 0))
            }
        }
    }

    fun getAllTickets(): Flow<List<OrderTicket>> {
        return flowOf(orderTickets)
    }

    fun getOrderTicketById(ticketId: Long): OrderTicket {
        return orderTickets.first {
            it.ticket.id == ticketId
        }
    }

    fun updateOrderTicket(ticketId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderTickets.indexOfFirst { it.ticket.id == ticketId }
        val result = if (index >= 0) {
            val orderTicket = orderTickets[index]
            orderTickets[index] =
                orderTicket.copy(ticket = orderTicket.ticket, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderTickets(): Flow<List<OrderTicket>> {
        return getAllTickets()
            .map { orderTickets ->
                orderTickets.filter { order ->
                    order.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: TicketRepository? = null

        fun getInstance(): TicketRepository =
            instance ?: synchronized(this) {
                TicketRepository().apply {
                    instance = this
                }
            }
    }
}