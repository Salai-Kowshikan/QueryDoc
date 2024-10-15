import React from "react";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import Chart from "@/components/Chart";
import { FaArrowLeft } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function Crud() {
    const navigate = useNavigate();
  return (
    <>
      <div className="">
      <button
        onClick={() => navigate("/")}
        className="absolute top-4 left-4 bg-gray-700 text-white p-2 rounded-full hover:bg-gray-600 transition duration-300 flex items-center"
      >
        <FaArrowLeft />
      </button>
        <Tabs defaultValue="create"  className="dark flex flex-col items-center justify-center ">
          <TabsList>
            <TabsTrigger value="create">Create</TabsTrigger>
            <TabsTrigger value="read">Read</TabsTrigger>
            <TabsTrigger value="update">Update</TabsTrigger>
            <TabsTrigger value="delete">Delete</TabsTrigger>
          </TabsList>
          <div>
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
          </div>
        </Tabs>
      </div>
    </>
  );
}

export default Crud;
