import React from "react";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import Chart from "@/components/Chart";

function Crud() {
  return (
    <>
      <div className="flex">
        <Tabs defaultValue="create" className="w-[400px]">
          <TabsList>
            <TabsTrigger value="create">Create</TabsTrigger>
            <TabsTrigger value="read">Read</TabsTrigger>
            <TabsTrigger value="update">Update</TabsTrigger>
            <TabsTrigger value="delete">Delete</TabsTrigger>
          </TabsList>
          <TabsContent value="create">
            <Chart operationType={"Create"} />
          </TabsContent>
          <TabsContent value="read">
            <Chart operationType={"Read"} />
          </TabsContent>
          <TabsContent value="update">
            <Chart operationType={"Update"} />
          </TabsContent>
          <TabsContent value="delete">
            <Chart operationType={"Delete"} />
          </TabsContent>
        </Tabs>
      </div>
    </>
  );
}

export default Crud;
