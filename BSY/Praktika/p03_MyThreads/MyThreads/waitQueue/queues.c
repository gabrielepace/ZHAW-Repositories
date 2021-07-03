//******************************************************************************
// File:    queues.c
// Purpose: implementation of queues for scheduler
// Author:  M. Thaler, 2012, (based on former work by J. Zeman and M. Thaler)
//          M. Thaler, 2019, CFS
// Version: v.fs20
//******************************************************************************

#include <stdlib.h>
#include <sys/types.h>
#include <sys/times.h>
#include <unistd.h>
#include <stdio.h>

#include "mlist.h"
#include "mthread.h"
#include "queues.h"

//******************************************************************************
// local function prototypes

unsigned mqGetTime(void);
void     mqPrintReadyQueueStatus(void);
mlist_t* sortQueue(mlist_t* runQueue, mlist_t* tmpQueue);
int compare (void *a, void *b);
int compareTime (void *a, void *b);

//******************************************************************************
// Static data of queueing system

mlist_t*    runQueue;                       // the run queue
mlist_t*    tmpQueue;                       // the run queue
mlist_t*    waitQueue;

//******************************************************************************
//
// Queueing functions
//
//==============================================================================
// Function:    initQueues
// Purpose:     initialize queueing system
//              behaves like a singleton

static int queueState = 0;

void mqInit(void) {
    if (queueState == 0) {
        runQueue = mlNewList();
        tmpQueue = mlNewList();
        waitQueue = mlNewList();
        mqGetTime();                        // register start time
        queueState = 1;                     // now we are initialized
    }
}

//==============================================================================
// Function:    delQueues
// Purpose:     clean up dynamically allocated data
// Hint:        will be called by scheduler before termination

void mqDelete(void) {
    mlDelList(runQueue);
    mlDelList(tmpQueue);
    mlDelList(waitQueue);
    queueState = 0;
    printf("\n*** cleaning queues ***\n");
}

//==============================================================================
// Function:    getNextThread
// Purpose:     returns thread with highest priority in the run queue
//              if there is no thread, return value is NULL

mthread_t* mqGetNextThread(void) {
  mthread_t* next_thread;
  mthread_t* test;
  if(mlReadFirst(waitQueue) != NULL){
    for (mthread_t* thread = mlReadFirst(waitQueue); thread !=NULL; thread = mlReadFirst(waitQueue)){
        if(thread->readyTime <= mqGetTime()){
          thread = mlDequeue(waitQueue);
          thread->vRuntime = 0;
        mlEnqueue(runQueue, thread);
      }
      else{
        //Es gibt noch keinen aufgewachten Thread, loop verlassen
        break;
      }
    }
  }
  runQueue = sortQueue(runQueue, tmpQueue);
  test = mlReadFirst(runQueue);
    if(test != NULL){
      next_thread = mlDequeue(runQueue);
    }
// bei leerer runQue, aber nicht leerer waitQue, lange genug (usec = TICS plus Reserve) warten bis der erste Thread in der waitQue wieder ausführbar ist.
  if (mlGetNumNodes(runQueue) == 0 && mlGetNumNodes(waitQueue)>0){
  usleep((mtGetReadyTime(mlReadFirst(waitQueue)) - mqGetTime()) *2 );
  //rekursiver Aufruf
  return mqGetNextThread();
  }
    return next_thread;
}

mlist_t* sortQueue(mlist_t* runQueue, mlist_t* tmpQueue){
  mlist_t tempList;
  static float offset;
  int n = mlGetNumNodes(runQueue);
  for (mthread_t* thread = mlDequeue(runQueue); thread != NULL; thread = mlDequeue(runQueue)){
    thread->vRuntime = thread->vRuntime - VR_DEFAULT/(n-1) - offset;
    mlSortIn(tmpQueue, thread, compare);
  }
  tempList = *runQueue;
  *runQueue = *tmpQueue;
  *tmpQueue = tempList;
  if(mlReadFirst(runQueue)){
    offset = mlReadFirst(runQueue)->vRuntime;
  }
  return runQueue;
}

//Teilaufgabe 2
int compare (void *a, void *b){
  int ta, tb;
  ta = ((mthread_t *)a)->vRuntime;
  tb = ((mthread_t *)b)->vRuntime;
    return (ta >= tb);
}
// Teilaufgabe 3
int compareTime (void *a, void *b){
  int ta, tb;
  ta = ((mthread_t *)a)->readyTime;
  tb = ((mthread_t *)b)->readyTime;
    return (ta >= tb);
}

//==============================================================================
// Function:    add to queue
// Purpose:     initialize queueing system

void mqAddToQueue(mthread_t *tcb, int sleepTime) {
  int prio;
  prio = tcb->tPrio+1;
  tcb->vRuntime = tcb->vRuntime + mqGetRuntime() * prio;
  tcb->readyTime = mqGetTime() + sleepTime;
  // Threads ohne sleeptime direkt in runQueue
  if(sleepTime > 0){
    mlSortIn(waitQueue, tcb, compareTime);
  }else{
    mlEnqueue(runQueue, tcb);
  }
}

//==============================================================================
// Function:    printWaitQueue
// Purpose:     prints a the list with threads in the wait queue

void lqPrintWaitQueue(void) {
}

//==============================================================================
// Function:    printReadyQueueStatus
// Purpose:     prints the number of threads in the ready queues at "getTime()"

void mqPrintReadyQueueStatus(void) {
    int i;
    i = mlGetNumNodes(runQueue); // number of threads
    printf("\t\trun queue,  %d entries at time %d\n", i, mqGetTime());
    i = mlGetNumNodes(tmpQueue); // number of threads
    printf("\t\ttmp queue,  %d entries at time %d\n", i, mqGetTime());
}

//******************************************************************************
// Function:    getTime, local function
// Purpose:     returns wall clock time in 1ms resolution since program start
//              works like singleton to register start time of module

unsigned mqGetTime(void) {
    static int    firstCall = 1;
    static struct timeval startTime;

    struct timeval currentTime;
    unsigned time;

    if (firstCall) {
        gettimeofday(&startTime, NULL);
        firstCall = 0;
    }

    gettimeofday(&currentTime, NULL);
    time = (unsigned)((currentTime.tv_sec  - startTime.tv_sec)*1000 +
                      (currentTime.tv_usec - startTime.tv_usec)/1000);
    return time;
}

//******************************************************************************