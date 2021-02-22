# Multithreaded Elevator Simulator 

## Özetçe / Abstract
- Bu projenin amacı bir AVM’deki asansörlere gelen isteklerdeki yoğunluğu, multithread kullanarak diğer asansörlerle birlikte azaltmaktır. Bu projedeki senaryoda alışveriş merkezindeki kat sayısı beştir. Toplamda beş adet asansör bulunmaktadır. Bu asansörlerin biri sürekli çalışmaktadır, geriye kalanlar yoğunluk durumuna göre aktif veya pasif durumuna geçmektedir. Asansörlerin maksimum kapasitesi 10’dur. Asansörlerdeki katlar arası geçiş 200’ms dir. Müşterilerin alışveriş merkezine girmesini ve giriş katta asansör kuyruğunda beklemesini kontrol eden bir login thread, diğer katlardan alışveriş merkezinden çıkmak için rasgele sayıda müşteriyi o katın asansör kuyruğuna ekleyen exit thread, ve yoğunluk durumuna göre yeni bir asansör threadini aktif veya pasif hale getiren kontrol threadi yer almaktadır. Bu projede java dili kullanılmış olup intellij idea editöründen yararlanılmıştır. Proje sonunda öngörülen kazanımlar elde edilmiş olup, multi threading kavramı öğrenilmiştir.

- The aim of this project is to reduce the density of requests to elevators in a shopping mall together with other elevators by using multithread. In the scenario in this project, the number of floors in the shopping center is five. There are five elevators in total. One of these elevators works continuously, the rest switch to active or passive state depending on the density. The maximum capacity of the elevators is 10. The transition between floors in elevators is 200ms. There is a login thread that controls customers entering the shopping center and waiting in the elevator queue at the entrance floor, an exit thread that adds random numbers of customers to the elevator queue of that floor to exit the shopping center from other floors, and a control thread that activates or deactivates a new elevator thread according to the density. . In this project java language was used and intellij idea editor was used. At the end of the project, the predicted gains were obtained and the concept of multithreading was learned.

## Kullanılan Teknolojiler 
- Java core 

## Geliştiriciler / Developers 
Berkay Efe ÖZCAN , Cumali TOPRAK
